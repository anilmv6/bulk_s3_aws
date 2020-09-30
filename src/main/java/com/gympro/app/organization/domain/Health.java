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
@Table(name = "org_health")
@Setter
@Getter
public class Health extends BaseEntity {

    @Column(name = "allergies") private String allergies;

    @Column(name = "healthIssue") private String healthIssue;

    @Column(name = "dietPlan") private String dietPlan;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Health(Long id) {
        super(id);
    }

    public Health() {
    }
}
