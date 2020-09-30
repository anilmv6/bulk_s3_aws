package com.gympro.app.organization.service;

import com.gympro.app.base.db.QueryFactory;
import com.gympro.app.base.exception.BusinessException;
import com.gympro.app.organization.domain.BaseEmployee;
import com.gympro.app.organization.domain.QBaseEmployee;
import com.gympro.app.organization.domain.Receptionist;
import com.gympro.app.organization.repository.EmployeeRepository;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Receptionist findReceptionistByEmployeeId(String employeeId) {
        QBaseEmployee qBaseEmployee = QBaseEmployee.baseEmployee;
        JPAQuery query = QueryFactory.createJPAQuery(entityManager, qBaseEmployee, BaseEmployee.class);
        BaseEmployee employee = query.where(qBaseEmployee.employeeId.eq(employeeId)).uniqueResult(qBaseEmployee);
        if (employee instanceof Receptionist) {
            return (Receptionist) employee;
        }
        throw new BusinessException("receptionist");
    }
}
