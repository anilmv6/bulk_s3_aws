package com.gympro.app.organization.controller;

import com.gympro.app.base.converter.EnvelopeConverter;
import com.gympro.app.base.type.request.RequestContext;
import com.gympro.app.base.type.request.RequestEnvelope;
import com.gympro.app.base.type.response.ResponseEnvelope;
import com.gympro.app.organization.dto.PointOfSaleDTO;
import com.gympro.app.organization.mapper.EntityMapper;
import com.gympro.app.organization.service.PointOfSaleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organization")
public class PointOfSaleController {

    private PointOfSaleService pointOfSaleService;
    private EntityMapper entityMapper;

    public PointOfSaleController(PointOfSaleService pointOfSaleService, EntityMapper entityMapper) {
        this.pointOfSaleService = pointOfSaleService;
        this.entityMapper = entityMapper;
    }

    @PostMapping(value = "/pos", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(httpMethod = "POST", value = "Post a PointOfSale", response = PointOfSaleDTO.class, nickname = "createPointOfSale")
    @ApiImplicitParams(
        @ApiImplicitParam(name = "PointOfSale", required = true, value = "PointOfSale Details", dataType = "PointOfSale",
            paramType = "body", type = "body")
    )
    public ResponseEntity<ResponseEnvelope> createPos(@ApiParam(name = "PointOfSale") @RequestBody RequestEnvelope request) {

        RequestContext context = RequestContext.getRequestContext();
        PointOfSaleDTO pointOfSaleDTO = EnvelopeConverter.extractFromEnvelop(request, PointOfSaleDTO.class);
        pointOfSaleDTO = entityMapper.createPointOfSaleDTO(pointOfSaleService.createPointOfsale(pointOfSaleDTO));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
            .createResponseEnvelop(context, null, "1200", pointOfSaleDTO);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.CREATED);
    }

}
