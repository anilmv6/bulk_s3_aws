package com.gympro.app.organization.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gympro.app.base.db.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "org_bulk_data_temp")
@Setter
@Getter
public class Student extends BaseEntity {

	@Column(name = "NAME")
	public String name;
	@Column(name = "AGE")
	public String age;
	@Column(name = "CAT")
	public String cat;
	@Column(name = "LNAME")
	public String lname;

	public Student(Long id) {
		super(id);
	}

	public Student() {
	}
}
