package com.gympro.app.base.scheduler;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gympro.app.base.sms.service.SMSNotificationService;
import com.gympro.app.base.sms.service.SchedulerService;
import com.gympro.app.organization.domain.SMSNotification;
import com.gympro.app.organization.dto.SendSMSDTO;

@Component
public class SMSScheduler {
    
    @Autowired
    private SchedulerService schedulerService;
    
    @Autowired
    private SMSNotificationService smsNotificationService;
    
    private static final Logger logger = LoggerFactory.getLogger(SMSScheduler.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
   // @Scheduled(fixedDelayString = "30000")
    public void triggerBeforeExpiryLicence() {
	logger.info("Now scheduler triggering at BeforeExpiryLicence start !!! {} ", dateTimeFormatter.format(LocalTime.now()));
	try {
	     List<SMSNotification>  list = schedulerService.findByDate();
	     System.out.println(list);
	    // for (SMSNotification smsNotification : list) {
		 Map<String, String> map=new HashMap<>();
		 map.put("Power World Gym", "+919986076463");
		 SendSMSDTO sendSMSDTO = new SendSMSDTO();
		 sendSMSDTO.setNamesAndMobileNumbers(map);
		 sendSMSDTO.setLicenceExpiryAlert(true);
		 
		 smsNotificationService.sendLicenceExpiryAlertSMS(sendSMSDTO);
		 
	    //}
	     
	     
	} catch (Exception e) {

	}
	logger.info("Now scheduler triggering at BeforeExpiryLicence end !!! {} ", dateTimeFormatter.format(LocalTime.now()));
    }

}
