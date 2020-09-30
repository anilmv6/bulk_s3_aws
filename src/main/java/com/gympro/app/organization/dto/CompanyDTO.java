package com.gympro.app.organization.dto;

import com.gympro.app.base.type.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@ApiModel("Company")
public class CompanyDTO extends BaseDTO {

    private String companyEmail;

    private String companyName;

    private Set<PointOfSaleDTO> pointOfSaleDTOs = new HashSet<>();
    
}
