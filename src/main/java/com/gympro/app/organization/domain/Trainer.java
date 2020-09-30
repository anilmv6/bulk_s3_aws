//package com.gympro.app.organization.domain;
//
//import com.gympro.app.auth.base.domain.BaseEntity;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "trainer", schema = "organization")
//@Setter
//@Getter
//public class Trainer extends BaseEntity {
//
//    @Column(name = "user_email") private String userEmail;
//
//    @Column(name = "pos_name") private String posName;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "employee_id")
//    private Employee employee;
//
//    public Trainer() {
//    }
//
//    public Trainer(Long id) {
//        super(id);
//    }
//}
