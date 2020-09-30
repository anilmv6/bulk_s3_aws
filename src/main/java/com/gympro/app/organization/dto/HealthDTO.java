package com.gympro.app.organization.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@ApiModel("Health")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HealthDTO extends BaseDTO {
    private String allergies;

    private String healthIssue;

    private String dietPlan;
}
