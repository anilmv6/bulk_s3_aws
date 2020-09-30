package com.gympro.app.base.db.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class CompanyEntity extends BaseEntity {

    @Column(name = "COMPANY_ID", nullable = false)
    private String companyId;

    public CompanyEntity() {
    }

    public CompanyEntity(Long id) {
        super(id);
    }


    public void setCompanyId(String posId) {
        this.companyId = posId;
    }

    public String getCompanyId() {
        return companyId;
    }
}
