package com.gympro.app.base.db.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PosCompanyEntity extends CompanyEntity {

    @Column(name = "POS_ID")
    private String posId;


    public PosCompanyEntity() {
    }

    public PosCompanyEntity(Long id) {
        super(id);
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getPosId() {
        return posId;
    }
}
