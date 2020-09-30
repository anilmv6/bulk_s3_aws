package com.gympro.app.organization.domain;


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
@Table(name = "org_company")
@Setter
@Getter
public class Company extends BaseEntity {

    @Column(name = "company_email", unique = true)
    @NotNull
    private String companyEmail;

    @Column(name = "company_name")
    @NotNull
    private String companyName;

    @OneToMany(
        mappedBy = "company",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<PointOfSale> pointOfSales = new HashSet<>();

//    @OneToMany(
//        mappedBy = "company",
//        fetch = FetchType.EAGER,
//        cascade = CascadeType.ALL,
//        orphanRemoval = true
//    )
//    private Set<Employee> posEmployees = new HashSet<>();

    public Company() {
    }

    public Company(Long id) {
        super(id);
    }

}
