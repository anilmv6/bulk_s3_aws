package com.gympro.app.organization.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gympro.app.base.type.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@ApiModel("Customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO extends BaseDTO {

    private String firstName;

    private String lastName;

    private LocalDateTime birthDate;

    private String gender;

    private String image;

    private String martialStatus;

    private String customerEmail;

    private String customerType;

    private String managedBy;

    @JsonProperty("phones")
    private Set<PhoneDTO> phoneDTOs = new HashSet<>();

    @JsonProperty("addresses")
    private Set<CustomerAddressDTO> addressesDtos = new HashSet<>();

    @JsonProperty("messages")
    private Set<MessageDTO> messages;

    @JsonProperty("social")
    private SocialDTO social;

    @JsonProperty("health")
    private HealthDTO health;

    @JsonProperty("payment")
    private PaymentDTO payment;

    @JsonProperty("work")
    private WorkDTO work;

    @JsonProperty("customerSettings")
    private CustomerSettingsDTO customerSettings;
    
}
