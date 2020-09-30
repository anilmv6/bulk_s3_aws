package com.gympro.app.organization.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.gympro.app.organization.domain.Company;
import com.gympro.app.organization.domain.Customer;
import com.gympro.app.organization.domain.CustomerAddress;
import com.gympro.app.organization.domain.Phone;
import com.gympro.app.organization.domain.PointOfSale;
import com.gympro.app.organization.domain.SMSNotification;
import com.gympro.app.organization.dto.CompanyDTO;
import com.gympro.app.organization.dto.CustomerAddressDTO;
import com.gympro.app.organization.dto.CustomerDTO;
import com.gympro.app.organization.dto.PhoneDTO;
import com.gympro.app.organization.dto.PointOfSaleDTO;
import com.gympro.app.organization.dto.SMSNotificationDTO;
import com.gympro.app.organization.dto.SendSMSDTO;

public class DtoMapper {

	

	public static SMSNotificationDTO createSMSNotificationDTO(SMSNotification smsNotification) {
		SMSNotificationDTO smsNotificationObj = new SMSNotificationDTO();
		BeanUtils.copyProperties(smsNotification, smsNotificationObj);
		return smsNotificationObj;
	}
	
	public static SendSMSDTO createSendSMSDTO(SMSNotification smsNotification) {
		SendSMSDTO sendSMSDTO = new SendSMSDTO();
		BeanUtils.copyProperties(smsNotification, sendSMSDTO);
		return sendSMSDTO;
	}

	public static List<SMSNotificationDTO> createSMSNotificationListDTO(List<SMSNotification> smsNotification) {
		List<SMSNotificationDTO> smsNotificationDTOList = new ArrayList<SMSNotificationDTO>();
		smsNotification.forEach(action -> {
			SMSNotificationDTO smsNotificationDTO = new SMSNotificationDTO();
			BeanUtils.copyProperties(action, smsNotificationDTO);
			smsNotificationDTOList.add(smsNotificationDTO);
		});
		return smsNotificationDTOList;
	}

	
}
