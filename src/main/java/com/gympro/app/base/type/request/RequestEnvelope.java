package com.gympro.app.base.type.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestEnvelope {
    private LocalDateTime requestDateTime;
    private Map<String, Object> data = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getData() {
        return data;
    }

    @JsonAnySetter
    public void setData(String key, Object data) {
        this.data.put(key, data);
    }
}
