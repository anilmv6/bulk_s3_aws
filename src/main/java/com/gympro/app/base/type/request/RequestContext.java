package com.gympro.app.base.type.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class RequestContext {
    private String requestId;
    private String userId;
    private String clientId;
    private String posId;
    private String companyId;

    private static ThreadLocal<RequestContext> requestContextThreadLocal = new ThreadLocal<>();

    public static RequestContext createRequestContext(String requestId, String userId, String clientId,
                                                                                    String posId, String companyId) {
        requestContextThreadLocal.set(new RequestContext(requestId, userId, clientId, posId, companyId));
        return requestContextThreadLocal.get();
    }

    private RequestContext(String requestId, String userId, String clientId, String posId, String companyId) {
        this.requestId = requestId;
        this.userId = userId;
        this.clientId = clientId;
        this.posId = posId;
        this.companyId = companyId;
    }
    public static RequestContext getRequestContext() {
        return requestContextThreadLocal.get();
    }

    public static void destroyRequestContext() {
        requestContextThreadLocal.remove();
    }
}
