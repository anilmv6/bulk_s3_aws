package com.gympro.app.base.sms.service;

import java.net.URLEncoder;
import java.text.Collator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.gympro.app.base.db.EntityFactory;
import com.gympro.app.base.sms.config.SMSConfig;
import com.gympro.app.base.sms.util.SMSUtils;
import com.gympro.app.base.type.request.RequestContext;
import com.gympro.app.organization.domain.Customer;
import com.gympro.app.organization.domain.Phone;
import com.gympro.app.organization.domain.SMSNotification;
import com.gympro.app.organization.dto.SMSNotificationDTO;
import com.gympro.app.organization.dto.SendSMSDTO;
import com.gympro.app.organization.repository.SMSNotificationRepository;
import com.gympro.app.organization.service.CustomerService;

@Service
public class SMSNotificationService {

	@Autowired
	private SMSNotificationRepository smsNotificationRepository;
	@Autowired
	private EntityFactory entityFactory;
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SMSConfig smsConfig;
	
	String string = null;

	public List<SMSNotification> findAll() throws Exception {
		return smsNotificationRepository.findAll();
	}

	public SMSNotification findById(SMSNotificationDTO smsNotificationDTO) throws Exception {
		SMSNotification smsNotification = null;

		Optional<SMSNotification> sMSNotificationObject = smsNotificationRepository
				.findById(smsNotificationDTO.getId());

		if (sMSNotificationObject.isPresent()) {
			smsNotification = sMSNotificationObject.get();
		}
		return smsNotification;
	}

	public void removeById(Long id) throws Exception {
		smsNotificationRepository.deleteById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public SMSNotification save(SMSNotificationDTO smsNotificationDTO) throws Exception {
		RequestContext context = RequestContext.getRequestContext();
		SMSNotification smsNotificationObject = entityFactory.newEntity(SMSNotification.class);
		BeanUtils.copyProperties(smsNotificationDTO, smsNotificationObject);
		smsNotificationObject.setEnabled(smsNotificationDTO.getIsEnabled());
		smsNotificationObject.setRequestId(context.getRequestId());
		SMSNotification smsNotification = null;
		try {
			smsNotification = smsNotificationRepository.save(smsNotificationObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return smsNotification;
	}

	public String sendAndSaveSMS(SendSMSDTO sendSms) throws Exception {
		StringBuilder postdata = new StringBuilder();
		List<String> stringOne = new ArrayList<String>();
		Map<String, String> nameAndMobileNumber = new HashMap<>();
		List<Customer> customerList = customerService.findByCustomerId(sendSms.getCustomerId());
		System.out.println(customerList);
		
		for (Customer customer : customerList) {
			
			List<Phone> phoneList = customer.getPhones().stream().filter
					(predicate -> predicate.getPreferred()==true).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(phoneList)) {
				nameAndMobileNumber.put(customer.getFirstName(), "+"+phoneList.stream().findFirst().get().getPhoneNumber());
			}
		}
		sendSms.setNamesAndMobileNumbers(nameAndMobileNumber);
		
		for (Map.Entry<String, String> entry : sendSms.getNamesAndMobileNumbers().entrySet()) {
		    System.out.println(entry.getKey() + " = " + entry.getValue());
		String stringBuilderMsg=SMSUtils.getCustomMessageBuilder(sendSms,entry.getKey()).toString();
		postdata.append("&mobiles=" + entry.getValue());
		postdata.append("&message=" + URLEncoder.encode(stringBuilderMsg));

		 String string = smsConfig.sendSMS(sendSms,postdata);
		 stringOne.add(string);
		System.out.println(string);

		}
		return stringOne.toString();
	}

	public String sendLicenceExpiryAlertSMS(SendSMSDTO sendSms) throws Exception {
		StringBuilder postdata = new StringBuilder();
		List<String> stringOne = new ArrayList<String>();
		for (Map.Entry<String, String> entry : sendSms.getNamesAndMobileNumbers().entrySet()) {
		    System.out.println(entry.getKey() + " = " + entry.getValue());
		String stringBuilderMsg=SMSUtils.getCMBuilderForLicenseEandR(sendSms,entry.getKey()).toString();
		postdata.append("&mobiles=" + entry.getValue());
		postdata.append("&message=" + URLEncoder.encode(stringBuilderMsg));

		 String string = smsConfig.sendSMS(sendSms,postdata);
		 stringOne.add(string);
		System.out.println(string);

		}
		return stringOne.toString();
	}
	
}
