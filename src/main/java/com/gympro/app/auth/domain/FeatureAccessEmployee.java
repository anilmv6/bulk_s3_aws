package com.gympro.app.auth.domain;

import com.gympro.app.base.db.domain.PosCompanyEntity;
import com.gympro.app.base.db.domain.PosEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "FEATURE_ACCESS_EMPLOYEE", uniqueConstraints = {@UniqueConstraint(columnNames = {"FEATURE_ID", "EMPLOYEE_ID"})})
@Setter
@Getter
public class FeatureAccessEmployee extends PosCompanyEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FEATURE_ID", referencedColumnName = "FEATURE_ID")
    private FeatureAccess feature;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
    private User user;

    public FeatureAccessEmployee(Long id) {
        super(id);
    }

    public FeatureAccessEmployee() {
    }

    @Override
    public String toString() {
        return "FeatureAccessEmployee{" +
                "feature=" + feature +
                ", user=" + user +
                "} " + super.toString();
    }
}
