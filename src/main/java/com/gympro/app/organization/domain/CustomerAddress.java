package com.gympro.app.organization.domain;


import com.gympro.app.base.db.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "org_address")
@Setter
@Getter
public class CustomerAddress extends BaseEntity {

    @Column(name = "address_type")
    private String addressType;

    @Column(name = "line1")
    private String line1;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "preferred")
    private Boolean preferred;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public CustomerAddress(Long id) {
        super(id);
    }

    public CustomerAddress() {
    }
}
