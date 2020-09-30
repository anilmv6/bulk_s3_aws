package com.gympro.app.organization.service;

import com.gympro.app.base.db.EntityFactory;
import com.gympro.app.base.type.request.RequestContext;
import com.gympro.app.organization.domain.PointOfSale;
import com.gympro.app.organization.domain.QPointOfSale;
import com.gympro.app.organization.dto.PointOfSaleDTO;
import com.gympro.app.organization.repository.PointOfSaleRepository;
import com.gympro.app.organization.repository.XRepository;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class PointOfSaleService extends AbstractService<PointOfSale> {

    private PointOfSaleRepository pointOfSaleRepository;
    private CompanyService companyService;

    private EntityFactory entityFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public PointOfSaleService(EntityFactory
        entityFactory, PointOfSaleRepository pointOfSaleRepository,
        CompanyService companyService) {
        this.entityFactory = entityFactory;
        this.pointOfSaleRepository = pointOfSaleRepository;
        this.companyService = companyService;
    }

    @Override
    protected String getEntityName() {
        return PointOfSale.class.getSimpleName();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public PointOfSale createPointOfsale(PointOfSaleDTO pointOfSaleDTO) {
        RequestContext context = RequestContext.getRequestContext();
        PointOfSale pointOfSale = entityFactory.newEntity(PointOfSale.class, pointOfSaleDTO);
        pointOfSale.setPosEmail(pointOfSaleDTO.getPosEmail());
        pointOfSale.setPosName(pointOfSaleDTO.getPosName());
        pointOfSale.setCompany(companyService.findCompanyById(context,Long.parseLong(context.getCompanyId())));
        return pointOfSaleRepository.save(pointOfSale);
    }

    public PointOfSale findPointOfSaleById(RequestContext context, Long posId) {
        JPAQuery query = new JPAQuery(entityManager);
        QPointOfSale qPointOfSale = QPointOfSale.pointOfSale;
        return query.from(qPointOfSale)
            .where(qPointOfSale.id.eq(posId)).uniqueResult(qPointOfSale);
    }
    @Override
    public XRepository<PointOfSale> getRepository() {
        return this.pointOfSaleRepository;
    }



}

