package com.gympro.app.organization.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BulkUploadRequest {

	private Long posId;
	private String fileName;
	private String fileType;
	
}
