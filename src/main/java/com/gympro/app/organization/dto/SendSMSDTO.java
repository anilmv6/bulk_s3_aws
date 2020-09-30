package com.gympro.app.organization.dto;

import java.util.List;
import java.util.Map;

import com.gympro.app.base.type.base.BaseDTO;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@ApiModel("Sms")
public class SendSMSDTO extends BaseDTO{

	private Long posId;
	private List<Long> customerId;
	private String type;
	private String offers;
	private String onDate;
	private String endTime;
	private String endDate;
	private String coupons;
	private String content;
	private String startDate;
	private String startTime;
	private String senderId;
	private String category;
	private String discounts;
	private String newClasses;
	private String couponCode;
	private List<String> nameList;
	private String responseMessage;
	private String classesScheduling;
	private String discountPercentage;
	private String newClassesRescheduling;
	private List<String> contactNumbersList;
	private Map<String,String> namesAndMobileNumbers;
	
	private Boolean isEnabled;
	private Long version;
	
	private boolean licenceExpiryAlert;
	
	
	
	
}
