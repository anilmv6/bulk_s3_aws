package com.gympro.app.organization.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@ApiModel("Payment")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDTO extends BaseDTO {

    private Boolean fullAmountPaid;

    private Boolean partPayment;

    private BigInteger amountPending;

    private BigInteger totalAmountToBePaid;

    private BigInteger amountPaid;

}
