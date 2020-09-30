package com.gympro.app.organization.controller;

import com.gympro.app.base.converter.EnvelopeConverter;
import com.gympro.app.base.rest.PageDetails;
import com.gympro.app.base.type.request.RequestContext;
import com.gympro.app.base.type.request.RequestEnvelope;
import com.gympro.app.base.type.response.ListResponseEnvelope;
import com.gympro.app.base.type.response.ResponseEnvelope;
import com.gympro.app.organization.domain.Message;
import com.gympro.app.organization.dto.CustomerDTO;
import com.gympro.app.organization.dto.MessageDTO;
import com.gympro.app.organization.mapper.EntityMapper;
import com.gympro.app.organization.service.CustomerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/organization/customer")
public class CustomerController {

    private CustomerService customerService;
    private EntityMapper entityMapper;

    public CustomerController(CustomerService customerService, EntityMapper entityMapper) {
        this.customerService = customerService;
        this.entityMapper = entityMapper;
    }

    @PostMapping(value = "/lead", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(httpMethod = "POST", value = "Post a Lead", response = CustomerDTO.class, nickname = "createLead")
    @ApiImplicitParams(
        @ApiImplicitParam(name = "Customer", required = true, value = "Lead Details", dataType = "Customer",
            paramType = "body", type = "body")
    )
    public ResponseEntity<ResponseEnvelope> createLead(
        @ApiParam(name = "Customer") @RequestBody RequestEnvelope request) {
        CustomerDTO customerDTO = EnvelopeConverter.extractFromEnvelop(request, CustomerDTO.class);
        customerDTO = entityMapper.createCustomerDTO(customerService.createLead(customerDTO));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
            .createResponseEnvelop(RequestContext.getRequestContext(), null, "1200", customerDTO);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.CREATED);
    }

    @GetMapping("/leadsByUser")
    @ApiOperation(value = "Get All Leads", notes = "Get Customers for a Lead.", response = CustomerDTO.class)
    public ResponseEntity<ListResponseEnvelope> findAllLeadsForCurrentUser(
            @ApiParam(value = "limit of the page") @RequestParam(name = "limit", required = false) Long limit,
            @ApiParam(value = "page number") @RequestParam(name = "page", required = false) Long page,
            @ApiParam(value = "sort type", allowableValues = "none, asc, desc", defaultValue = "none") @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "sortBy", required = false) String sortBy) {
        RequestContext context = RequestContext.getRequestContext();
        PageDetails pageDetails = new PageDetails(limit, PageDetails.SortType.forValue(sort), sortBy, page);
        List<CustomerDTO> leads = customerService.listAllLeadsForCurrentEmployee(context, pageDetails);
        ListResponseEnvelope responseEnvelope = EnvelopeConverter
                .createListResponseEnvelop(RequestContext.getRequestContext(), null, "1200", leads);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.OK);
    }

    @PostMapping(value = "/lead/messageContent", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(httpMethod = "POST", value = "Post a Message", response = MessageDTO.class, nickname = "createMessage")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Message", required = true, value = "Message Details", dataType = "Message",
                    paramType = "body", type = "body")
    )
    public ResponseEntity<ResponseEnvelope> addMessage(@ApiParam(name = "Message") @RequestBody RequestEnvelope request) {
        MessageDTO message = EnvelopeConverter.extractFromEnvelop(request, MessageDTO.class);
        message = entityMapper.convertMessage(customerService.addMessage(message));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(RequestContext.getRequestContext(), null, "1200", message);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.CREATED);
    }

    @PutMapping(value = "/lead/messageContent", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(httpMethod = "PUT", value = "Update a Message", response = MessageDTO.class, nickname = "updateMessage")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Message", required = true, value = "Message Details", dataType = "Message",
                    paramType = "body", type = "body")
    )
    public ResponseEntity<ResponseEnvelope> updateMessage(@ApiParam(name = "Message") @RequestBody RequestEnvelope request) {
        MessageDTO message = EnvelopeConverter.extractFromEnvelop(request, MessageDTO.class);
        message = entityMapper.convertMessage(customerService.updateMessage(message));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(RequestContext.getRequestContext(), null, "1200", message);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/lead/messageContent", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(httpMethod = "DELETE", value = "Delete a Message", response = MessageDTO.class, nickname = "updateMessage")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Message", required = true, value = "Message Details", dataType = "Message",
                    paramType = "body", type = "body")
    )
    public ResponseEntity<ResponseEnvelope> deleteMessage(@ApiParam(name = "Message") @RequestBody RequestEnvelope request) {
        MessageDTO message = EnvelopeConverter.extractFromEnvelop(request, MessageDTO.class);
        message = entityMapper.convertMessage(customerService.deleteMessage(message));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(RequestContext.getRequestContext(), null, "1200", message);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.CREATED);
    }

}
