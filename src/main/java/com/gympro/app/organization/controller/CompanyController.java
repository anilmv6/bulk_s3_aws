package com.gympro.app.organization.controller;

import com.gympro.app.base.converter.EnvelopeConverter;
import com.gympro.app.base.type.request.RequestContext;
import com.gympro.app.base.type.request.RequestEnvelope;
import com.gympro.app.base.type.response.ResponseEnvelope;
import com.gympro.app.organization.dto.CompanyDTO;
import com.gympro.app.organization.mapper.EntityMapper;
import com.gympro.app.organization.service.CompanyService;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organization")
public class CompanyController {

    private CompanyService companyService;
    private EntityMapper entityMapper;

    public CompanyController(CompanyService companyService, EntityMapper entityMapper) {
        this.companyService = companyService;
        this.entityMapper = entityMapper;
    }

    @Timed
    @PostMapping(value = "/company", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(httpMethod = "POST", value = "Post a Company", response = CompanyDTO.class, nickname = "createCompany")
    @ApiImplicitParams(
        @ApiImplicitParam(name = "Company", required = true, value = "Company Details", dataType = "Company",
            paramType = "body", type = "body")
    )
    public ResponseEntity<ResponseEnvelope> createCompany(@ApiParam(name = "Company") @RequestBody
        RequestEnvelope request) {
        CompanyDTO companyDTO = EnvelopeConverter.extractFromEnvelop(request, CompanyDTO.class);
        companyDTO = entityMapper.createCompanyDTO(companyService.createCompany(companyDTO));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
            .createResponseEnvelop(RequestContext.getRequestContext(), null, "1200", companyDTO);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.CREATED);
    }
}
