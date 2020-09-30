package com.gympro.app.organization.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gympro.app.base.type.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@ApiModel("CustomerSettings")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerSettingsDTO extends BaseDTO {

    private LocalDateTime registrationDate;

    private LocalDateTime joinedDate;

    private LocalDateTime memberExpirationDate;

    private Boolean freeclasses;
}
