package com.gympro.app.base.db.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PosEntity extends BaseEntity {

    @Column(name = "POS_ID", nullable = false)
    private String posId;

    public PosEntity() {
    }

    public PosEntity(Long id) {
        super(id);
    }


    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getPosId() {
        return posId;
    }
}
