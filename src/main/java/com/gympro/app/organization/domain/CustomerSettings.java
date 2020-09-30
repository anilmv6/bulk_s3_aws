package com.gympro.app.organization.domain;

import com.gympro.app.base.db.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "org_customer_settings")
@Setter
@Getter
public class CustomerSettings extends BaseEntity {

    @Column(name = "registrationDate") private LocalDateTime registrationDate;

    @Column(name = "joinedDate") private LocalDateTime joinedDate;

    @Column(name = "memberExpirationDate") private LocalDateTime memberExpirationDate;

    @Column(name = "freeclasses") private Boolean freeclasses;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public CustomerSettings(Long id) {
        super(id);
    }

    public CustomerSettings() {
    }
}
