package com.gympro.app.base.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gympro.app.base.converter.EnvelopeConverter;
import com.gympro.app.base.sms.service.SMSNotificationService;
import com.gympro.app.base.type.request.RequestContext;
import com.gympro.app.base.type.request.RequestEnvelope;
import com.gympro.app.base.type.response.ResponseEnvelope;
import com.gympro.app.organization.domain.SMSNotification;
import com.gympro.app.organization.dto.SMSNotificationDTO;
import com.gympro.app.organization.dto.SendSMSDTO;
import com.gympro.app.organization.mapper.DtoMapper;
import com.gympro.app.organization.util.Constants;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/sms")
public class SMSController {

	@Autowired
	public SMSNotificationService sMSNotificationService;

	/**
	 * Method retrieves all the sms information from the Database
	 *
	 * @return Collection of smss JSON
	 */

	@GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SMSNotificationDTO>> findAll() throws Exception {
		List<SMSNotification> listNotification = sMSNotificationService.findAll();
		List<SMSNotificationDTO> listSMS = DtoMapper.createSMSNotificationListDTO(listNotification);
		// this is sample requestContext, we need to remove once finalize the response
		return new ResponseEntity(listSMS, HttpStatus.OK);
	}

	/**
	 * Method retrieves the sms information using the ID provided in the Path
	 * Parameter
	 * 
	 * @param id
	 */
	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<ResponseEnvelope> findById(
			@ApiParam(value = "sms id", required = true) @PathVariable("id") Long id,
			@ApiParam(value = "pos id", required = false) @RequestParam("posId") Long posId) throws Exception {

		SMSNotificationDTO smsNotificationDTO = new SMSNotificationDTO();
		smsNotificationDTO.setId(id);
		smsNotificationDTO.setPosId(posId);
		SMSNotificationDTO sendsSmsdto = DtoMapper
				.createSMSNotificationDTO(sMSNotificationService.findById(smsNotificationDTO));
		// this is sample requestContext, we need to remove once finalize the response
		ResponseEnvelope responseEnvelope = EnvelopeConverter.createResponseEnvelop(RequestContext.createRequestContext("requestContext", "anilkumar", "1", "7000", "1"), null, "1200",
				sendsSmsdto);
		return new ResponseEntity(responseEnvelope, HttpStatus.OK);

	}

	/**
	 * Method deletes the sms information using the ID provided in the Path
	 * Parameter
	 *
	 * @param id
	 */
	@DeleteMapping("/removeById/{id}")
	public ResponseEntity<Void> removeById(@ApiParam(value = "sms id", required = true) @PathVariable("id") Long id)
			throws Exception {
		sMSNotificationService.removeById(id);
		return new ResponseEntity(HttpStatus.OK);

	}

	/**
	 * Method saves the sms template information JSON to the database
	 * 
	 * @param sms
	 * @return sms JSON with Database ID
	 */
	@PostMapping(value = "/smsTemplate/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEnvelope> save(
			@ApiParam(name = "SMSNotification") @RequestBody RequestEnvelope request,
			@ApiParam(value = Constants.POS_ID, required = false) @RequestHeader(value = Constants.POS_ID, required = false) final Long posId)
			throws Exception {
		
		SMSNotificationDTO smsNotification = EnvelopeConverter.extractFromEnvelop(request, SMSNotificationDTO.class);
		smsNotification.setPosId(posId);
		SMSNotificationDTO sendSMSDTO = DtoMapper
				.createSMSNotificationDTO(sMSNotificationService.save(smsNotification));
		RequestContext context = RequestContext.getRequestContext();
		ResponseEnvelope responseEnvelope = EnvelopeConverter.createResponseEnvelop(context, null, "1200",
				sendSMSDTO);
		return new ResponseEntity<>(responseEnvelope, HttpStatus.CREATED);
	}

	

}
