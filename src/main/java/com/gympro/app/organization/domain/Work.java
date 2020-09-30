package com.gympro.app.organization.domain;

import com.gympro.app.base.db.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "org_work")
@Setter
@Getter
public class Work extends BaseEntity {

    @Column(name = "company") private String company;

    @Column(name = "company_email") private String companyEmail;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Work(Long id) {
        super(id);
    }

    public Work() {
    }
}
