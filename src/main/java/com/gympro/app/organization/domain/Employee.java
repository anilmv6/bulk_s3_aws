//package com.gympro.app.organization.domain;
//
//import com.gympro.app.auth.base.domain.BaseEntity;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "employee", schema = "organization")
//@Setter
//@Getter
//public class Employee extends BaseEntity {
//
//    @Column(name = "role") private String companyEmail;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id")
//    private Company company;
//
//    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private PosEmployee posEmployee;
//
//    @OneToMany(
//        mappedBy = "employee",
//        fetch = FetchType.EAGER,
//        cascade = CascadeType.ALL,
//        orphanRemoval = true
//    )
//    private Set<Admin> admins = new HashSet<>();
//
//    @OneToMany(
//        mappedBy = "employee",
//        fetch = FetchType.EAGER,
//        cascade = CascadeType.ALL,
//        orphanRemoval = true
//    )
//    private Set<Manager> managers = new HashSet<>();
//
//    @OneToMany(
//        mappedBy = "employee",
//        fetch = FetchType.EAGER,
//        cascade = CascadeType.ALL,
//        orphanRemoval = true
//    )
//    private Set<Trainer> trainers = new HashSet<>();
//
//    public Employee() {
//    }
//
//    public Employee(Long id) {
//        super(id);
//    }
//}
