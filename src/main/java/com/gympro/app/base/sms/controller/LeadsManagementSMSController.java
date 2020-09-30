package com.gympro.app.base.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gympro.app.base.converter.EnvelopeConverter;
import com.gympro.app.base.sms.service.SMSNotificationService;
import com.gympro.app.base.type.request.RequestContext;
import com.gympro.app.base.type.request.RequestEnvelope;
import com.gympro.app.base.type.response.ResponseEnvelope;
import com.gympro.app.organization.dto.SendSMSDTO;
import com.gympro.app.organization.util.Constants;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/sms")
public class LeadsManagementSMSController {

	@Autowired
	public SMSNotificationService smsNotificationService;

	/**
	 * Method saves the sms information JSON to the database
	 * 
	 * @param smss
	 * @return sms JSON with Database ID
	 */
	@PostMapping("/leadsManagement/sendSMS")
	public ResponseEntity<ResponseEnvelope> sendAndSaveSMS(
			@ApiParam(value = Constants.POS_ID, required = false) @RequestHeader(value = Constants.POS_ID, required = false) final Long posId,
			@ApiParam(name = "send SMS") @RequestBody RequestEnvelope request) throws Exception {
		SendSMSDTO sendSMS = null;
		sendSMS = EnvelopeConverter.extractFromEnvelop(request, SendSMSDTO.class);
		sendSMS.setPosId(posId);
		String response = smsNotificationService.sendAndSaveSMS(sendSMS);
		SendSMSDTO sendSMSDTO = new SendSMSDTO();
		sendSMSDTO.setResponseMessage(response);
		RequestContext context = RequestContext.getRequestContext();
		ResponseEnvelope responseEnvelope = EnvelopeConverter.createResponseEnvelop(context, null, "1200", sendSMSDTO);
		return new ResponseEntity(HttpStatus.OK);

	}

	/**
	 * Method saves the sms information JSON to the database
	 * 
	 * @param smss
	 * @return sms JSON with Database ID
	 */
	@PostMapping("/sendSMS")
	public ResponseEntity<ResponseEnvelope> sendSMS(
			@ApiParam(value = Constants.POS_ID, required = false) @RequestHeader(value = Constants.POS_ID, required = false) final Long posId,
			@ApiParam(name = "send SMS") @RequestBody RequestEnvelope request) throws Exception {

		SendSMSDTO sendSMS = EnvelopeConverter.extractFromEnvelop(request, SendSMSDTO.class);
		sendSMS.setPosId(posId);
		String response = smsNotificationService.sendAndSaveSMS(sendSMS);
		SendSMSDTO sendSMSDTO = new SendSMSDTO();
		sendSMSDTO.setResponseMessage(response);
		RequestContext context = RequestContext.getRequestContext();
		ResponseEnvelope responseEnvelope = EnvelopeConverter.createResponseEnvelop(context, null, "1200", sendSMSDTO);
		return new ResponseEntity(HttpStatus.OK);

	}
}
