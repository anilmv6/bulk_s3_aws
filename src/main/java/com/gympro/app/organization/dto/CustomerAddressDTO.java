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
@ApiModel("CustomerAddress")
public class CustomerAddressDTO extends BaseDTO {

    private String addressType;

    private String line1;

    private String city;

    private String state;

    private String postalCode;

    private String country;

    private Boolean preferred;
}
