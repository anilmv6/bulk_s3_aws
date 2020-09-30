package com.gympro.app.organization.mapper;

import com.gympro.app.organization.domain.Company;
import com.gympro.app.organization.domain.Customer;
import com.gympro.app.organization.domain.CustomerAddress;
import com.gympro.app.organization.domain.CustomerSettings;
import com.gympro.app.organization.domain.Health;
import com.gympro.app.organization.domain.Message;
import com.gympro.app.organization.domain.Payment;
import com.gympro.app.organization.domain.Phone;
import com.gympro.app.organization.domain.PointOfSale;
import com.gympro.app.organization.domain.Social;
import com.gympro.app.organization.domain.Work;
import com.gympro.app.organization.dto.CompanyDTO;
import com.gympro.app.organization.dto.CustomerAddressDTO;
import com.gympro.app.organization.dto.CustomerDTO;
import com.gympro.app.organization.dto.CustomerSettingsDTO;
import com.gympro.app.organization.dto.HealthDTO;
import com.gympro.app.organization.dto.MessageDTO;
import com.gympro.app.organization.dto.PaymentDTO;
import com.gympro.app.organization.dto.PhoneDTO;
import com.gympro.app.organization.dto.PointOfSaleDTO;
import com.gympro.app.organization.dto.SocialDTO;
import com.gympro.app.organization.dto.WorkDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    @Mapping(source = "pointOfSales", target = "pointOfSaleDTOs")
    CompanyDTO createCompanyDTO(Company company);

    PointOfSaleDTO createPointOfSaleDTO(PointOfSale pointOfSale);

    PhoneDTO createCustomerPhoneDTO(Phone phone);

    CustomerAddressDTO createCustomerAddressDTO(CustomerAddress customerAddress);

    SocialDTO createCustomerSocial(Social social);

    HealthDTO createCustomerHealth(Health health);

    PaymentDTO createCustomerPayment(Payment payment);

    WorkDTO createCustomerWork(Work work);

    CustomerSettingsDTO createCustomerSettings(CustomerSettings customerSettings);

    @Mapping(source = "phones", target = "phoneDTOs")
    @Mapping(source = "customerAddresses", target = "addressesDtos")
    @Mapping(target = "managedBy", ignore = true)
    @Mapping(target = "social")
    @Mapping(target = "health")
    @Mapping(target = "payment")
    @Mapping(target = "work")
    @Mapping(target = "customerSettings")
    @Mapping(target = "messages")
    CustomerDTO createCustomerDTO(Customer customer);

    @Mapping(target = "message", expression = "java(new String(message.getMessageContent()))")
    MessageDTO convertMessage(Message message);
}
