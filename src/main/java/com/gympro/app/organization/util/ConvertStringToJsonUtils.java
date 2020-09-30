package com.gympro.app.organization.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.gympro.app.organization.dto.BulkUploadRequest;

public class ConvertStringToJsonUtils {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	public static BulkUploadRequest getObj(final String jsonString) throws Exception {
	    try {
	      return getObjReader(new TypeReference<BulkUploadRequest>() {}).readValue(jsonString);
	    } catch (Exception e) {
	    	e.printStackTrace();
	      throw new Exception();
	    }
	  }

	  private static <T> ObjectReader getObjReader(TypeReference<BulkUploadRequest> typeReference) {
	    return objectMapper.readerFor(typeReference);
	  }
}
