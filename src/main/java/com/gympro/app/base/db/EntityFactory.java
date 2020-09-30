package com.gympro.app.base.db;

import com.gympro.app.base.db.domain.BaseEntity;
import com.gympro.app.base.db.domain.CompanyEntity;
import com.gympro.app.base.db.domain.PosCompanyEntity;
import com.gympro.app.base.db.domain.PosEntity;
import com.gympro.app.base.type.base.BaseDTO;
import com.gympro.app.base.type.request.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Service
public class EntityFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityFactory.class);

    private SequenceGenerator sequenceGenerator;

    public EntityFactory(SequenceGenerator sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    public <T extends BaseEntity> T newEntity(Class<T> clazz, BaseDTO baseDTO) {
        T entity = newEntity(clazz);
        RequestContext context = RequestContext.getRequestContext();
        entity.setCreatedBy(context.getUserId());
        entity.setCreatedDatetime(baseDTO.getRequestDateTime());
        entity.setUpdatedBy(context.getUserId());
        entity.setUpdatedDatetime(baseDTO.getRequestDateTime());
        entity.setEnabled(true);
        entity.setVersion(1L);
        entity.setRequestId(context.getRequestId());
        if (entity instanceof PosEntity) {
            PosEntity posEntity = (PosEntity) entity;
            posEntity.setPosId(context.getPosId());
        }
        if (entity instanceof CompanyEntity) {
            CompanyEntity companyEntity = (CompanyEntity) entity;
            companyEntity.setCompanyId(context.getCompanyId());
        }
        if (entity instanceof PosCompanyEntity) {
            PosCompanyEntity posCompanyEntity = (PosCompanyEntity) entity;
            posCompanyEntity.setPosId(context.getPosId());
            posCompanyEntity.setCompanyId(context.getCompanyId());
        }
        return entity;
    }

    public <T extends BaseEntity> T newEntity(Class<T> clazz) {
        Constructor<T> constructor;
        T entity;
        try {
            constructor = clazz.getConstructor(Long.class);
        } catch (NoSuchMethodException e) {
            LOGGER.error("ERROR:");
            throw new IllegalArgumentException(e);
        }

        try {
            entity = constructor.newInstance(sequenceGenerator.nextId());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("ERROR:");
            throw new IllegalArgumentException(e);
        }
        return entity;
    }
}
