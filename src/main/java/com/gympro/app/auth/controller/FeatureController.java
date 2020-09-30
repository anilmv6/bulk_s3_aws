package com.gympro.app.auth.controller;

import com.gympro.app.auth.domain.FeatureAccess;
import com.gympro.app.auth.dto.FeatureAccessDTO;
import com.gympro.app.auth.dto.FeaturesDTO;
import com.gympro.app.auth.mapper.AuthMapper;
import com.gympro.app.auth.service.FeatureAccessService;
import com.gympro.app.auth.util.DtoFactory;
import com.gympro.app.base.annotation.HttpRequestContext;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/auth/features")
public class FeatureController {

    private FeatureAccessService featureAccessService;

    private AuthMapper authMapper;

    public FeatureController(FeatureAccessService featureAccessService, AuthMapper authMapper) {
        this.featureAccessService = featureAccessService;
        this.authMapper = authMapper;
    }

    @PostMapping
    @ApiOperation(httpMethod = "POST", value = "post a Feature", response = FeatureAccessDTO.class, nickname = "createFeature")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "FeatureAccess", required = true, value = "Feature Details", dataType = "FeatureAccess",
                    paramType = "body", type = "body")
    )
    public ResponseEntity<ResponseEnvelope> createFeatureAccess(@ApiParam(name = "FeatureAccess") @RequestBody RequestEnvelope request) {
        RequestContext context = RequestContext.getRequestContext();
        FeatureAccessDTO featureAccessDTO = EnvelopeConverter.extractFromEnvelop(request, FeatureAccessDTO.class);
        FeatureAccess featureAccess = featureAccessService.saveFeatureAccess(context, featureAccessDTO);
        featureAccessDTO.setId(featureAccess.getId());
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(context, null, "1200", featureAccessDTO);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.CREATED);
    }

    @GetMapping("/{featureAccessId}")
    @ApiOperation(value = "Get FeatureAccess", notes = "Get FeatureAccess.", response=FeatureAccessDTO.class)
    public ResponseEntity<ResponseEnvelope> findFeatureAccess(@ApiIgnore @HttpRequestContext RequestContext context, @PathVariable("featureAccessId") String featureAccessId) {
        FeatureAccessDTO featureAccessDTO = authMapper.convertFeatureAccess(featureAccessService.findFeatureAccessById(context, featureAccessId));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(context, null, "1200", featureAccessDTO);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.OK);
    }
    @GetMapping("/all")
    @ApiOperation(value = "Get All FeatureAccess", notes = "Get FeatureAccess.", response = FeaturesDTO.class)
    public ResponseEntity<ResponseEnvelope> getAllFeatures() {
        RequestContext context = RequestContext.getRequestContext();
        FeaturesDTO features = featureAccessService.getAllFeatureAccess();
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(context, null, "1200", features);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.OK);
    }


}
