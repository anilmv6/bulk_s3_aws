package com.gympro.app.base.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gympro.app.base.type.request.RequestContext;
import com.gympro.app.base.type.request.RequestEnvelope;
import com.gympro.app.base.type.response.ListResponseEnvelope;
import com.gympro.app.base.type.response.ResponseContext;
import com.gympro.app.base.type.response.ResponseEnvelope;
import com.gympro.app.base.type.response.ResponseError;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class EnvelopeConverter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().findAndRegisterModules();

    public static <T> T extractFromEnvelop(RequestEnvelope envelope, Class<T> clazz) {
        Map<String, Object> data = envelope.getData();
       // data.put("requestDateTime", envelope.getRequestDateTime());
        return OBJECT_MAPPER.convertValue(data, clazz);
    }

    public static ResponseEnvelope createResponseEnvelop(RequestContext requestContext, List<ResponseError> errors,
                                                         String responseCode, Object data) {
        return new ResponseEnvelope(
                new ResponseContext(requestContext.getRequestId(), requestContext.getClientId()),
                LocalDateTime.now(), errors, responseCode,
                OBJECT_MAPPER.convertValue(data, new TypeReference<Map<String, Object>>() {
                }));
    }
    public static ListResponseEnvelope createListResponseEnvelop(RequestContext requestContext, List<ResponseError> errors,
                                                                 String responseCode, Object data) {
        return new ListResponseEnvelope(
                new ResponseContext(requestContext.getRequestId(), requestContext.getClientId()),
                LocalDateTime.now(), errors, responseCode,
                OBJECT_MAPPER.convertValue(data, new TypeReference<List<Map<String, Object>>>() {
                }));
    }
}
