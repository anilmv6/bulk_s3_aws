package com.gympro.app.base.sms.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gympro.app.organization.domain.QSMSNotification;
import com.gympro.app.organization.domain.SMSNotification;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
public class SchedulerService {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<SMSNotification> findByDate() {
        JPAQuery query = new JPAQuery(entityManager);
        LocalDateTime localDateTime =  LocalDateTime.now().plusDays(5);
        QSMSNotification qsmsNotification = QSMSNotification.sMSNotification;
        return query.from(qsmsNotification)
            .where(qsmsNotification.updatedDatetime.gt(localDateTime)).list(qsmsNotification);
    }
}
