package com.gympro.app.organization.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gympro.app.organization.util.Constants;

@Configuration
public class BulkUploadConfig {
	
	public void saveFileToAWS(File file) throws IOException {
		getAWSAccess().putObject(Constants.FILE_STORAGE_BUCKET, file.getName(), file);
	}
	
	public InputStream downloadFileFromAWS(String fileName) throws IOException {
		return getAWSAccess().getObject(Constants.FILE_STORAGE_BUCKET, fileName).getObjectContent();
	}
	
	public AmazonS3 getAWSAccess() {
		
		AWSCredentials credentials = new BasicAWSCredentials("AKIAIXUTSBOVKT45OUVQ",
				"gxN59AN8DorV6xy6io7Oukt4EvoyOwN4WgfF3mQK");
		
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.AP_SOUTH_1).build();

	}
	
	 /**
		 * method to return ObjectMapper
		 * 
		 * @return ObjectMapper
		 */
		@Bean
		public ObjectMapper objectMapper() {

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
			objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

			return objectMapper;
		}
}
