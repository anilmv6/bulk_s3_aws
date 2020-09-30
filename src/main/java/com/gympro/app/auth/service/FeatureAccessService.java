package com.gympro.app.auth.service;

import com.gympro.app.auth.domain.FeatureAccess;
import com.gympro.app.auth.dto.FeatureAccessDTO;
import com.gympro.app.auth.dto.FeaturesDTO;
import com.gympro.app.auth.mapper.AuthMapper;
import com.gympro.app.auth.respository.FeatureAccessRepository;
import com.gympro.app.base.db.EntityFactory;
import com.gympro.app.auth.domain.QFeatureAccess;
import com.gympro.app.base.db.QueryFactory;
import com.gympro.app.base.type.request.RequestContext;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.support.Expressions;
import com.mysema.query.types.Expression;
import com.mysema.query.types.Operator;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.path.StringPath;
import org.springframework.data.querydsl.binding.QuerydslPredicateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

@Service
public class FeatureAccessService {

    private FeatureAccessRepository featureAccessRepository;
    private EntityFactory entityFactory;
    private AuthMapper authMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public FeatureAccessService(FeatureAccessRepository featureAccessRepository, EntityFactory entityFactory, AuthMapper authMapper) {
        this.featureAccessRepository = featureAccessRepository;
        this.entityFactory = entityFactory;
        this.authMapper = authMapper;
    }

    @Transactional(readOnly = true)
    public FeatureAccess findFeatureAccessById(RequestContext context, String featureId) {
        QFeatureAccess qFeatureAccess = QFeatureAccess.featureAccess;
        JPAQuery query = QueryFactory.createJPAQuery(entityManager, qFeatureAccess, FeatureAccess.class);
        return query.where(qFeatureAccess.featureId.eq(featureId)).uniqueResult(qFeatureAccess);
    }

    @Transactional(readOnly = true)
    public List<FeatureAccess> findAllFeatureAccess(RequestContext context) {
        return featureAccessRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public FeatureAccess saveFeatureAccess(RequestContext context, FeatureAccessDTO featureAccessDTO) {
        FeatureAccess featureAccess = entityFactory.newEntity(FeatureAccess.class, featureAccessDTO);
        authMapper.mapFeatureAccessDto(featureAccessDTO, featureAccess);
        return featureAccessRepository.save(featureAccess);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public FeatureAccess update(RequestContext context, FeatureAccess featureAccess) {
        return featureAccessRepository.save(featureAccess);
    }

    @Transactional(readOnly = true)
    public FeaturesDTO getAllFeatureAccess() {
        List<FeatureAccess> featureAccesses = featureAccessRepository.findAll();
        Map<String, List<FeatureAccessDTO>> featuresByGroup = featureAccesses.stream()
                                .collect(groupingBy(this::getFeatureGroup,
                                                mapping(access -> authMapper.convertFeatureAccess(access), toList())));
        FeaturesDTO featuresDTO = new FeaturesDTO();
        featuresDTO.setFeatures(featuresByGroup);
        return featuresDTO;
    }

    private String getFeatureGroup(FeatureAccess featureAccess) {
        int index = featureAccess.getFeatureId().indexOf("_");
        return featureAccess.getFeatureId().substring(0, index);
    }
}
