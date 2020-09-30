package com.gympro.app.organization.domain;

import com.gympro.app.base.db.domain.CompanyEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "org_employee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "employee_type", discriminatorType = DiscriminatorType.STRING)
public class BaseEmployee extends CompanyEntity {

    @Column(name = "employee_id", unique = true)
    @NotNull
    private String employeeId;

    @Column(name = "first_name") private String firstName;

    @Column(name = "last_name") private String lastName;

    @Column(name = "birth_date") private LocalDateTime birthDate;

    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "martial_status") private String martialStatus;

    @Column(name = "email") private String employeeEmail;

    @Column(name = "designation") private String designation;

    @Column(name = "rating")
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private BaseEmployee supervisor;

    @OneToMany(
            mappedBy = "employee",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<PosEmployee> posEmployees = new HashSet<>();

    @OneToMany(
            mappedBy = "supervisor",
            fetch = FetchType.LAZY
    )
    private Set<BaseEmployee> reportees = new HashSet<>();

    public BaseEmployee() {
    }

    public BaseEmployee(Long id) {
        super(id);
    }

    public void addPosEmployee(PosEmployee posEmployee) {
        posEmployees.add(posEmployee);
        posEmployee.setEmployee(this);
    }

    public void removePosEmployee(PosEmployee posEmployee) {
        posEmployees.remove(posEmployee);
        posEmployee.setEmployee(null);
    }

    public void addReportee(BaseEmployee baseEmployee) {
        reportees.add(baseEmployee);
        baseEmployee.setSupervisor(this);
    }

    public void removeReportee(BaseEmployee baseEmployee) {
        reportees.remove(baseEmployee);
        baseEmployee.setSupervisor(null);
    }
}
