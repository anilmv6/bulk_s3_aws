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
@ApiModel("PointOfSale")
public class PointOfSaleDTO extends BaseDTO {

    private String posEmail;

    private String posName;
    
}
