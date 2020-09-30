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
@Table(name = "org_more_details")
@Setter
@Getter
public class MoreDetails extends BaseEntity {

    @Column(name = "comments") private String comments;

    @Column(name = "referral") private Boolean referral;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public MoreDetails(Long id) {
        super(id);
    }

    public MoreDetails() {
    }
}
