package com.gympro.app.organization.domain;

import com.gympro.app.base.db.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "org_phone")
@Setter
@Getter
public class Phone extends BaseEntity {

    @Column(name = "phone_number")
    private BigInteger phoneNumber;

    @Column(name = "extension")
    private BigInteger extension;

    @Column(name = "phone_type")
    private String phoneType;

    @Column(name = "preferred")
    private Boolean preferred;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Phone(Long id) {
        super(id);
    }

    public Phone() {
    }
}
