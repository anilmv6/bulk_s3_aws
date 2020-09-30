package com.gympro.app.auth.domain;

import com.gympro.app.base.db.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "FEATURE_ACCESS")
@Setter
@Getter
public class FeatureAccess extends BaseEntity {

    @Column(name = "FEATURE_ID", unique = true)
    @NotNull
    private String featureId;

    @Column(name = "FEATURE_NAME")
    @NotNull
    private String featureName;

    @Column(name = "FEATURE_URL")
    @NotNull
    private String featureUrl;

    @Column(name = "FEATURE_METHOD")
    @NotNull
    private String method;

    @OneToMany(
            mappedBy = "feature",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.MERGE
    )
    private Set<FeatureAccessEmployee> featureAccessEmployees = new HashSet<>();

    public FeatureAccess(Long id) {
        super(id);
    }

    public FeatureAccess() {
    }

    public void addFeatureAccessEmployee(FeatureAccessEmployee featureAccessEmployee) {
        featureAccessEmployees.add(featureAccessEmployee);
        featureAccessEmployee.setFeature(this);
    }

    public void removeFeatureAccessEmployee(FeatureAccessEmployee featureAccessEmployee) {
        featureAccessEmployees.remove(featureAccessEmployee);
        featureAccessEmployee.setFeature(null);
    }
}
