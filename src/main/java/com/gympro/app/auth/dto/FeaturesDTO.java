package com.gympro.app.auth.dto;

import com.gympro.app.base.type.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@ApiModel("Features")
public class FeaturesDTO extends BaseDTO {
    private UserDTO user;
    private Map<String, List<FeatureAccessDTO>> features;
}
