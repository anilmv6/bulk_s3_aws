package com.gympro.app.organization.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gympro.app.base.type.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@ApiModel("Social")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SocialDTO extends BaseDTO {

    private String facebook;

    private String twitter;

    private String google;
}
