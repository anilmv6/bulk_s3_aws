package com.gympro.app.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gympro.app.organization.domain.BulkUploadTemplate;
import com.gympro.app.organization.service.BulkUploadService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/xls")
public class BulkUploadController {

	@Autowired
	private BulkUploadService bulkUploadService;
	/**
	 * saves the customer data information JSON to the database
	 * 
	 * @param MultipartFile
	 */
	@PostMapping(value = "/customerInfo/save")
	public ResponseEntity<BulkUploadTemplate> save(
			@ApiParam(name = "xlsValues") @RequestPart(required = false) String xlsValues,
			@ApiParam(name = "Customer Data")  @RequestPart(required = true) MultipartFile file)
			throws Exception {
		
		BulkUploadTemplate bulkUpload = bulkUploadService.uploadData(xlsValues, file);
		return new ResponseEntity<>(bulkUpload,HttpStatus.CREATED);
	}
}
