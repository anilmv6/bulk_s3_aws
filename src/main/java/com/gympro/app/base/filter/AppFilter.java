package com.gympro.app.base.filter;

import com.gympro.app.auth.service.UserService;
import com.gympro.app.base.type.request.RequestContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class AppFilter extends OncePerRequestFilter {
    private static final String REQUEST_ID = "RequestId";
    private static final String POS_ID = "PosId";
    private static final String COMPANY_ID = "CompanyId";
    private static final String CLIENT_ID = "";
    private static final String ERROR_JSON = "{\n" +
            "\"errorCode\" : \"1401\",\n" +
            "\"errorMessage\": \"User does not have access for this resource\"\n" +
            "}";

    private UserService userService;

    public AppFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String userId = null;
            if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
                if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String) {
                    userId= (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                }
                String requestId = request.getHeader(REQUEST_ID);
                if (requestId == null) {
                    requestId = UUID.randomUUID().toString();
                }
                String companyId = request.getHeader(COMPANY_ID);
                String posId = request.getHeader(POS_ID);
                RequestContext context = RequestContext.createRequestContext(requestId, userId, "GymPro", posId, companyId);
                validateResourceAccess(request, response, context, filterChain);
            } else {
                filterChain.doFilter(request, response);
            }
        } finally {
            RequestContext.destroyRequestContext();
        }
    }

    private void validateResourceAccess(HttpServletRequest request, HttpServletResponse response, RequestContext context, FilterChain filterChain) throws IOException, ServletException {
        String requestUri  = request.getRequestURI();
        String method = request.getMethod();
        if (!userService.validateFeatureAccess(context, requestUri, method)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().print(ERROR_JSON);
            response.getWriter().flush();
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
