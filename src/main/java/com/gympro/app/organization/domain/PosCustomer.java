package com.gympro.app.organization.domain;

import com.gympro.app.base.db.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "org_pos_customer", uniqueConstraints = {@UniqueConstraint(columnNames = {"pos_id", "customer_id"})})
@Setter
@Getter
public class PosCustomer extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "pos_id")
    private PointOfSale pointOfSale;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public PosCustomer(Long id) {
        super(id);
    }

    public PosCustomer() {
    }
}
