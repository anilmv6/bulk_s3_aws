package com.gympro.app.base.type.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ListResponseEnvelope {
    private ResponseContext context;
    private LocalDateTime responseDateTime;
    private List<ResponseError> errors;
    private String responseCode;
    private List<Map<String, Object>> data = new ArrayList<>();
}
