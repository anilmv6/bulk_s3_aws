package com.gympro.app.organization.domain;

import com.gympro.app.base.db.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "org_point_of_sale")
@Setter
@Getter
public class PointOfSale extends BaseEntity {

    @Column(name = "pos_email", unique = true)
    @NotNull
    private String posEmail;

    @Column(name = "pos_name")
    @NotNull
    private String posName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

//    @OneToOne(mappedBy = "pointOfSale", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private PosEmployee posEmployee;

    @OneToMany(
            mappedBy = "pointOfSale",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.MERGE
    )
    private Set<PosCustomer> posCustomers = new HashSet<>();

    @OneToMany(
            mappedBy = "pointOfSale",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.MERGE
    )
    private Set<PosEmployee> employees = new HashSet<>();

    public PointOfSale(Long id) {
        super(id);
    }

    public PointOfSale() {
    }

    public void addPosCustomer(PosCustomer posCustomer) {
        posCustomers.add(posCustomer);
        posCustomer.setPointOfSale(this);
    }

    public void removePosCustomer(PosCustomer posCustomer) {
        posCustomers.remove(posCustomer);
        posCustomer.setPointOfSale(null);
    }

    public void addPosEmployee(PosEmployee posEmployee) {
        employees.add(posEmployee);
        posEmployee.setPointOfSale(this);
    }

    public void removePosEmployee(PosEmployee posEmployee) {
        employees.remove(posEmployee);
        posEmployee.setPointOfSale(null);
    }
}
