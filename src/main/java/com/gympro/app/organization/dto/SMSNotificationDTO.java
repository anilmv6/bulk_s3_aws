package com.gympro.app.organization.dto;

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
@ApiModel("SMSNotification")
public class SMSNotificationDTO extends BaseDTO{

    private Long posId;
    private String category;
    private String type;
    private String content;
    private Boolean isEnabled;//enabled
    private Long version;
}
