package com.gympro.app.organization.service;


import com.gympro.app.base.db.EntityFactory;
import com.gympro.app.base.type.request.RequestContext;
import com.gympro.app.organization.domain.Company;
import com.gympro.app.organization.domain.Customer;
import com.gympro.app.organization.domain.PointOfSale;
import com.gympro.app.organization.domain.QCompany;
import com.gympro.app.organization.dto.CompanyDTO;
import com.gympro.app.organization.repository.CompanyRepository;
import com.gympro.app.organization.repository.XRepository;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CompanyService extends AbstractService<Company> {

    private final CompanyRepository companyRepository;

    private EntityFactory entityFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public CompanyService(EntityFactory
        entityFactory, CompanyRepository companyRepository) {
        this.entityFactory = entityFactory;
        this.companyRepository = companyRepository;
    }

    @Override
    protected String getEntityName() {
        return Customer.class.getSimpleName();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Company createCompany( CompanyDTO companyDTO) {
        Company company = entityFactory.newEntity(Company.class, companyDTO);
        company.setCompanyName(companyDTO.getCompanyName());
        company.setCompanyEmail(companyDTO.getCompanyEmail());
        Set<PointOfSale> pointOfSales = new HashSet<>();
        companyDTO.getPointOfSaleDTOs().forEach(pointOfSaleDTO -> {
            PointOfSale pointOfSale = entityFactory.newEntity(PointOfSale.class, pointOfSaleDTO);
            pointOfSale.setPosName(pointOfSaleDTO.getPosName());
            pointOfSale.setPosEmail(pointOfSaleDTO.getPosEmail());
            pointOfSale.setCompany(company);
            pointOfSales.add(pointOfSale);
        });
        company.setPointOfSales(pointOfSales);
        return companyRepository.save(company);
    }

    @Override
    public XRepository<Company> getRepository() {
        return this.companyRepository;
    }


    @Transactional(readOnly = true)
    public Company findCompanyById(RequestContext context, Long companyId) {
        JPAQuery query = new JPAQuery(entityManager);
        QCompany qCompany = QCompany.company;
        return query.from(qCompany)
            .where(qCompany.id.eq(companyId)).uniqueResult(qCompany);
    }
}

