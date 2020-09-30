package com.gympro.app.base.sms.util;

import java.util.List;

import org.springframework.util.StringUtils;

import com.gympro.app.organization.dto.SendSMSDTO;

public class SMSUtils {

	public static StringBuilder buildContactNumber(List<String> numberList) {

		boolean isAppend = false;
		StringBuilder buildContacts = new StringBuilder();

		List<String> contactNumbersList = numberList;
		for (String action : contactNumbersList) {

			if (isAppend) {
				buildContacts.append(",").append(action);
			} else {
				buildContacts.append(action);
				isAppend = true;
			}
		}
		return buildContacts;
	}
	
	public static StringBuilder getCustomMessageBuilder(SendSMSDTO sendSms,String name) {
		StringBuilder stringBuilder = new StringBuilder();

			stringBuilder.append(SMSConstant.HI);
			stringBuilder.append(SMSConstant.SPACE);
			stringBuilder.append(name);
			stringBuilder.append(SMSConstant.COMMA);
			stringBuilder.append(SMSConstant.SPACE);
			if (sendSms.getType().equals(SMSConstant.CLASSES_SCHEDULING)) {

				stringBuilder.append(SMSConstant.CLASSES_SCHEDULING_MSG);

				if (!StringUtils.isEmpty(sendSms.getOnDate())) {
					stringBuilder.append(SMSConstant.SPACE);
					stringBuilder.append(SMSConstant.ON);
					stringBuilder.append(SMSConstant.SPACE);
					stringBuilder.append(sendSms.getOnDate());
				}
				getFromToTimings(stringBuilder, sendSms);
			}
			
			if (sendSms.getType().equals(SMSConstant.CLASSES_RESCHEDULING)) {
				stringBuilder.append(SMSConstant.THE);
				stringBuilder.append(SMSConstant.SPACE);
				stringBuilder.append(sendSms.getOnDate());
				stringBuilder.append(SMSConstant.CLASSES_RESCHEDULING_MSG);
				stringBuilder.append(SMSConstant.ON);
				stringBuilder.append(SMSConstant.SPACE);
				stringBuilder.append(sendSms.getEndDate());
				
				getFromToTimings(stringBuilder, sendSms);
			}
			
			if (sendSms.getType().equals(SMSConstant.NEW_CLASSES)) {
				stringBuilder.append(SMSConstant.NEW_CLASSES_MSG);
				stringBuilder.append(SMSConstant.ON);
				stringBuilder.append(SMSConstant.SPACE);
				stringBuilder.append(sendSms.getOnDate());
			}
			
			if (sendSms.getType().equals(SMSConstant.COUPONS)) {
				stringBuilder.append(SMSConstant.COUPONS_MSG_ONE);
				stringBuilder.append(SMSConstant.SPACE);
				stringBuilder.append(sendSms.getCouponCode());
				stringBuilder.append(SMSConstant.SPACE);
				stringBuilder.append(SMSConstant.AND_SYMBAL);
				stringBuilder.append(SMSConstant.SPACE);
				stringBuilder.append(SMSConstant.GET);
				stringBuilder.append(SMSConstant.SPACE);
				stringBuilder.append(sendSms.getDiscountPercentage());
				stringBuilder.append(SMSConstant.SPACE);
				stringBuilder.append(SMSConstant.COUPONS_MSG_TWO);
			}
			
			if (sendSms.getType().equals(SMSConstant.DISCOUNTS)) {
				stringBuilder.append(SMSConstant.GET);
				stringBuilder.append(SMSConstant.SPACE);
				stringBuilder.append(sendSms.getDiscountPercentage());
				stringBuilder.append(SMSConstant.SPACE);
				stringBuilder.append(SMSConstant.COUPONS_MSG_TWO);
			}
			
			if (sendSms.getType().equals(SMSConstant.OFFERS)) {
				stringBuilder.append(SMSConstant.GET);
				stringBuilder.append(SMSConstant.SPACE);
				stringBuilder.append(sendSms.getDiscountPercentage());
				stringBuilder.append(SMSConstant.SPACE);
				stringBuilder.append(SMSConstant.OFFERS_MSG);
				stringBuilder.append(SMSConstant.SPACE);
				stringBuilder.append(sendSms.getEndDate());
				stringBuilder.append(SMSConstant.PERIOD);
			}

			stringBuilder.append(SMSConstant.CONTACT_GYMPRO);
		
		return stringBuilder;

	}
	
	
	public static StringBuilder getCMBuilderForLicenseEandR(SendSMSDTO sendSms,String name) {
		StringBuilder stringBuilder = new StringBuilder();

			stringBuilder.append(SMSConstant.HI);
			stringBuilder.append(SMSConstant.SPACE);
			stringBuilder.append(name);
			stringBuilder.append(SMSConstant.COMMA);
			stringBuilder.append(SMSConstant.SPACE);
			if (sendSms.isLicenceExpiryAlert()) {

				stringBuilder.append(SMSConstant.LICENSE_EXPIRE);

				if (!StringUtils.isEmpty(sendSms.getOnDate())) {
					stringBuilder.append(SMSConstant.SPACE);
					stringBuilder.append(SMSConstant.ON);
					stringBuilder.append(SMSConstant.SPACE);
					stringBuilder.append(sendSms.getOnDate());
				}
				stringBuilder.append(SMSConstant.LICENSE_EXPIRE_ONE);
			}
			stringBuilder.append(SMSConstant.CONTACT_GYMPRO);
		
		return stringBuilder;

	}

	public static void getFromToTimings(StringBuilder stringBuilder, SendSMSDTO sendSms) {

		if (!StringUtils.isEmpty(sendSms.getStartTime())) {
			stringBuilder.append(SMSConstant.SPACE);
			stringBuilder.append(SMSConstant.FROM);
			stringBuilder.append(SMSConstant.SPACE);
			stringBuilder.append(sendSms.getStartTime());
		}

		if (!StringUtils.isEmpty(sendSms.getEndTime())) {
			stringBuilder.append(SMSConstant.SPACE);
			stringBuilder.append(SMSConstant.TO);
			stringBuilder.append(SMSConstant.SPACE);
			stringBuilder.append(sendSms.getEndTime());
		}
	}
}
