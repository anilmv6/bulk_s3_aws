package com.gympro.app.organization.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gympro.app.base.db.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "org_bulk_upload_template")
@Setter
@Getter
public class BulkUploadTemplate extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -3970178652538731163L;

	@Column(name = "POS_ID")
	private Long posId;
	@Column(name = "TOTAL_NUMBER_OF_RECORDS")
	private Long totalNumberOfRecords;
	@Column(name = "FILE_NAME")
	private String filename;
	@Column(name = "FAILED_RECORD_COUNT")
	private Long failedRecordCount;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "TYPE")
	private String type;

	public BulkUploadTemplate(Long id) {
		super(id);
	}

	public BulkUploadTemplate() {
	}

}
