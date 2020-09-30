package com.gympro.app.auth.mapper;

import com.gympro.app.auth.domain.FeatureAccess;
import com.gympro.app.auth.domain.FeatureAccessEmployee;
import com.gympro.app.auth.domain.User;
import com.gympro.app.auth.dto.AddressDTO;
import com.gympro.app.auth.dto.FeatureAccessDTO;
import com.gympro.app.auth.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    void mapFeatureAccessDto(FeatureAccessDTO featureAccessDTO, @MappingTarget FeatureAccess featureAccess);
    FeatureAccessDTO convertFeatureAccess(FeatureAccess featureAccess);

    @Mapping(target = "password", constant = "masked_password")
    @Mapping(source = "email", target = "mailId")
    @Mapping(source = "featureAccessEmployees", target = "featureAccess")
    UserDTO convertUser(User user);

    @Mapping(target = "password", constant = "masked_password")
    @Mapping(source = "email", target = "mailId")
    @Mapping(target = "addresses", ignore = true)
    UserDTO convertOnlyUser(User user);

    @Mapping(target = "featureId", source = "featureAccessEmployee.feature.featureId")
    @Mapping(target = "method", source = "featureAccessEmployee.feature.method")
    @Mapping(target = "featureName", source = "featureAccessEmployee.feature.featureName")
    @Mapping(target = "featureUrl", source = "featureAccessEmployee.feature.featureUrl")
    FeatureAccessDTO convertFromFeatureAccessEmployee(FeatureAccessEmployee featureAccessEmployee);
}
