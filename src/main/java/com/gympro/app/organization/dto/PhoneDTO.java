package com.gympro.app.organization.dto;

import com.gympro.app.base.type.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@ApiModel("Phone")
public class PhoneDTO extends BaseDTO {

    private BigInteger phoneNumber;

    private BigInteger extension;

    private String phoneType;

    private Boolean preferred;
}
