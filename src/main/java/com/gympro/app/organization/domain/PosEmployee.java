package com.gympro.app.organization.domain;

import com.gympro.app.base.db.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "org_pos_employee", uniqueConstraints = {@UniqueConstraint(columnNames = {"pos_id", "employee_id"})})
@Setter
@Getter
public class PosEmployee extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "pos_id")
    private PointOfSale pointOfSale;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private BaseEmployee employee;

    public PosEmployee() {
    }

    public PosEmployee(Long id) {
        super(id);
    }
}
