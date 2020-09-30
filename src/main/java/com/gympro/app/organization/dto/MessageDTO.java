package com.gympro.app.organization.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@ApiModel("Message")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO extends BaseDTO {

    private Long customerId;
    @JsonProperty("message")
    private String message;
}
