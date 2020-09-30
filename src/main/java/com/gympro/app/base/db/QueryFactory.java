package com.gympro.app.base.db;

import com.gympro.app.base.db.domain.BaseEntity;
import com.gympro.app.base.db.domain.CompanyEntity;
import com.gympro.app.base.db.domain.PosCompanyEntity;
import com.gympro.app.base.db.domain.PosEntity;
import com.gympro.app.base.db.domain.QCompanyEntity;
import com.gympro.app.base.db.domain.QPosCompanyEntity;
import com.gympro.app.base.db.domain.QPosEntity;
import com.gympro.app.base.type.request.RequestContext;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.support.Expressions;
import com.mysema.query.types.Ops;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.StringPath;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

public class QueryFactory {
    public static <T extends BaseEntity, R extends EntityPathBase<T>> JPAQuery createJPAQuery(EntityManager entityManager,
                                                                                              R entityPath, Class<T> entityClass) {
        JPAQuery query = new JPAQuery(entityManager);
        query.from(entityPath);
        RequestContext context = RequestContext.getRequestContext();
        if (CompanyEntity.class.isAssignableFrom(entityClass)) {

            if (!StringUtils.isEmpty(context.getCompanyId())) {
                Path<?> whereEntity = Expressions.path(entityPath.getType(), entityPath.toString());
                Path<String> companyPath = Expressions.path(String.class, whereEntity, "companyId");
                query.getMetadata().addWhere(Expressions.predicate(Ops.EQ, companyPath, Expressions.constant(context.getCompanyId())));

            }
        }
        if (PosCompanyEntity.class.isAssignableFrom(entityClass)) {
            if (!StringUtils.isEmpty(context.getPosId())) {
                Path<?> whereEntity = Expressions.path(entityPath.getType(), entityPath.toString());
                Path<String> posPath = Expressions.path(String.class, whereEntity, "posId");
                query.getMetadata().addWhere(Expressions.predicate(Ops.EQ, posPath, Expressions.constant(context.getPosId())));
            }
        }
        return query;
    }
}
