package com.gympro.app.organization.domain;

import com.gympro.app.base.db.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "org_payment")
@Setter
@Getter
public class Payment extends BaseEntity {

    @Column(name = "fullAmountPaid") private Boolean fullAmountPaid;

    @Column(name = "partPayment") private Boolean partPayment;

    @Column(name = "amountPending") private BigInteger amountPending;

    @Column(name = "totalAmountToBePaid") private BigInteger totalAmountToBePaid;

    @Column(name = "amountPaid") private BigInteger amountPaid;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Payment(Long id) {
        super(id);
    }

    public Payment() {
    }
}
