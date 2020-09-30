package com.gympro.app.auth.controller;

import com.gympro.app.auth.dto.FeaturesDTO;
import com.gympro.app.auth.dto.UserDTO;
import com.gympro.app.auth.mapper.AuthMapper;
import com.gympro.app.auth.util.DtoFactory;
import com.gympro.app.base.annotation.HttpRequestContext;
import com.gympro.app.auth.service.UserService;
import com.gympro.app.base.converter.EnvelopeConverter;
import com.gympro.app.base.type.request.RequestContext;
import com.gympro.app.base.type.request.RequestEnvelope;
import com.gympro.app.base.type.response.ResponseEnvelope;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/auth/users")
public class UserController {

    private UserService userService;

    private AuthMapper authMapper;

    public UserController(UserService userService, AuthMapper authMapper) {
        this.userService = userService;
        this.authMapper = authMapper;
    }

    @PostMapping
    @ApiOperation(httpMethod = "POST", value = "post a user", response = UserDTO.class, nickname = "getUser")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "User", required = true, value = "User Details", dataType = "User",
                    paramType = "body", type = "body")
    )
    public ResponseEntity<ResponseEnvelope> createUser(@ApiParam(name = "User") @RequestBody RequestEnvelope request) {
        RequestContext context = RequestContext.getRequestContext();
        UserDTO user = EnvelopeConverter.extractFromEnvelop(request, UserDTO.class);
        user = DtoFactory.createUserDTO(userService.createUser(context, user));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(context, null, "1200", user);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "Get User", notes = "Get User.", response=UserDTO.class)
    public ResponseEntity<ResponseEnvelope> findUser(@ApiIgnore @HttpRequestContext RequestContext context, @PathVariable("userId") String userId) {
        UserDTO user = DtoFactory.createUserDTO(userService.findUserByEmail(context, userId));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(context, null, "1200", user);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.OK);
    }


    @PutMapping("/features")
    @ApiOperation(httpMethod = "PUT", value = "update feature access of a user", response = UserDTO.class, nickname = "getUser")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "User", required = true, value = "User Details", dataType = "User",
                    paramType = "body", type = "body")
    )
    public ResponseEntity<ResponseEnvelope> updateFeatureAccess(@ApiParam(name = "User") @RequestBody RequestEnvelope request) {
        RequestContext context = RequestContext.getRequestContext();
        UserDTO user = EnvelopeConverter.extractFromEnvelop(request, UserDTO.class);
        user = authMapper.convertUser(userService.updateFeatureAccess(context, user));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(context, null, "1200", user);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.OK);
    }

    @DeleteMapping("/features")
    @ApiOperation(httpMethod = "DELETE", value = "delete feature access from a user", response = UserDTO.class, nickname = "getUser")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "User", required = true, value = "User Details", dataType = "User",
                    paramType = "body", type = "body")
    )
    public ResponseEntity<ResponseEnvelope> deleteFeatureAccess(@ApiParam(name = "User") @RequestBody RequestEnvelope request) {
        RequestContext context = RequestContext.getRequestContext();
        UserDTO user = EnvelopeConverter.extractFromEnvelop(request, UserDTO.class);
        user = authMapper.convertUser(userService.removeFeatureAccess(context, user));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(context, null, "1200", user);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.OK);
    }

    @GetMapping("/features/{employeeId}")
    @ApiOperation(value = "Get All FeatureAccess by user", notes = "Get FeatureAccess for User.", response = FeaturesDTO.class)
    public ResponseEntity<ResponseEnvelope> getAllFeatures(@PathVariable("employeeId") String employeeId) {
        RequestContext context = RequestContext.getRequestContext();
        FeaturesDTO features = userService.findFeaturesByUser(context, employeeId);
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(context, null, "1200", features);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.OK);
    }
}
