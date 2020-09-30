package com.gympro.app.auth.service;

import com.gympro.app.auth.domain.FeatureAccess;
import com.gympro.app.auth.domain.FeatureAccessEmployee;
import com.gympro.app.auth.domain.QFeatureAccess;
import com.gympro.app.auth.domain.User;
import com.gympro.app.auth.dto.FeatureAccessDTO;
import com.gympro.app.auth.dto.FeaturesDTO;
import com.gympro.app.auth.dto.UserDTO;
import com.gympro.app.auth.mapper.AuthMapper;
import com.gympro.app.auth.respository.FeatureAccessEmployeeRepository;
import com.gympro.app.auth.respository.FeatureAccessRepository;
import com.gympro.app.auth.respository.UserRepository;
import com.gympro.app.base.db.EntityFactory;
import com.gympro.app.auth.domain.QUser;
import com.gympro.app.base.db.QueryFactory;
import com.gympro.app.base.type.request.RequestContext;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

@Service
public class UserService {

    private UserRepository userRepository;
    private FeatureAccessRepository featureAccessRepository;
    private FeatureAccessEmployeeRepository featureAccessEmployeeRepository;
    private FeatureAccessService featureAccessService;
    private EntityFactory entityFactory;
    private PasswordEncoder passwordEncoder;
    private AuthMapper authMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public UserService(EntityFactory entityFactory, UserRepository userRepository, FeatureAccessEmployeeRepository featureAccessEmployeeRepository,
                       FeatureAccessService featureAccessService,
                       @Qualifier("userPasswordEncoder")
                       @Lazy
                       PasswordEncoder passwordEncoder, AuthMapper authMapper, FeatureAccessRepository featureAccessRepository) {
        this.entityFactory = entityFactory;
        this.userRepository = userRepository;
        this.featureAccessEmployeeRepository = featureAccessEmployeeRepository;
        this.featureAccessService = featureAccessService;
        this.passwordEncoder = passwordEncoder;
        this.authMapper = authMapper;
        this.featureAccessRepository = featureAccessRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User createUser(RequestContext context, UserDTO userDTO) {
        User user = entityFactory.newEntity(User.class);
        user.setEmail(userDTO.getMailId());
        user.setEmployeeId(userDTO.getEmployeeId());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPosId(context.getPosId());
        user.setRequestId(context.getRequestId());
        user.setDesignation(userDTO.getDesignation());
        user.setEnabled(true);
        userDTO.getFeatureAccess().forEach(featureAccessDTO -> {
            FeatureAccess featureAccess = featureAccessService.findFeatureAccessById(context, featureAccessDTO.getFeatureId());
            FeatureAccessEmployee featureAccessEmployee = entityFactory.newEntity(FeatureAccessEmployee.class);
            featureAccessEmployee.setPosId(context.getPosId());
            featureAccessEmployee.setCompanyId(context.getCompanyId());
            featureAccessEmployee.setRequestId(context.getRequestId());
            featureAccessEmployee.setEnabled(true);
            user.addFeatureAccessEmployee(featureAccessEmployee);
            featureAccess.addFeatureAccessEmployee(featureAccessEmployee);
            userRepository.save(user);
        });
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUserByEmail(RequestContext context, String email) {
        QUser user = QUser.user;
        JPAQuery query = QueryFactory.createJPAQuery(entityManager, user, User.class);
        return query.where(user.email.eq(email)).uniqueResult(user);
    }

    @Transactional(readOnly = true)
    public User findUserByEmployeeId(RequestContext context, String employeeId) {
        QUser user = QUser.user;
        JPAQuery query = QueryFactory.createJPAQuery(entityManager, user, User.class);
        return query.where(user.employeeId.eq(employeeId)).uniqueResult(user);
    }


    @Transactional(readOnly = true)
    public List<FeatureAccess> findFeatureAccessByEmployeeId(RequestContext context, String employeeId) {
        JPAQuery query = new JPAQuery(entityManager);
        QUser qUser = QUser.user;
        User user =  query.from(qUser)
                .where(qUser.employeeId.eq(employeeId)).uniqueResult(qUser);
        return user.getFeatureAccessEmployees().stream()
                                .map(FeatureAccessEmployee::getFeature)
                                .collect(Collectors.toList());
    }

    @Transactional
    public User updateFeatureAccess(RequestContext context, UserDTO userDTO) {
        User user = getUser(context, userDTO);
        userDTO.getFeatureAccess().forEach(featureAccessDTO -> {
            boolean matched = user.getFeatureAccesses().stream()
                        .anyMatch(featureAccess -> featureAccess.getFeatureId().equals(featureAccessDTO.getFeatureId()));
            if (!matched) {
                FeatureAccess featureAccess = featureAccessService.findFeatureAccessById(context, featureAccessDTO.getFeatureId());
                FeatureAccessEmployee featureAccessEmployee = entityFactory.newEntity(FeatureAccessEmployee.class);
                featureAccessEmployee.setPosId(context.getPosId());
                featureAccessEmployee.setCompanyId(context.getCompanyId());
                featureAccessEmployee.setRequestId(context.getRequestId());
                featureAccessEmployee.setEnabled(true);
                user.addFeatureAccessEmployee(featureAccessEmployee);
                featureAccess.addFeatureAccessEmployee(featureAccessEmployee);
            }
        });
        return userRepository.save(user);
    }

    @Transactional
    public User removeFeatureAccess(RequestContext context, UserDTO userDTO) {
        User user = getUser(context, userDTO);
        userDTO.getFeatureAccess().forEach(featureAccessDTO -> {
            FeatureAccessEmployee userFeatureAccess = user.getFeatureAccessEmployees().stream()
                                        .filter(featureAccessEmployee -> featureAccessEmployee.getFeature().getFeatureId().equals(featureAccessDTO.getFeatureId()))
                                        .findFirst().orElse(null);
            FeatureAccess featureAccess = userFeatureAccess.getFeature();
            featureAccess.removeFeatureAccessEmployee(userFeatureAccess);
            user.removeFeatureAccessEmployee(userFeatureAccess);
            featureAccessEmployeeRepository.delete(userFeatureAccess);
        });
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public FeaturesDTO findFeaturesByUser(RequestContext context, String employeeId) {
        User user = findUserByEmployeeId(context, employeeId);
        Map<String, List<FeatureAccessDTO>> featuresByGroup = user.getFeatureAccesses().stream()
                .collect(groupingBy(this::getFeatureGroup,
                        mapping(access -> authMapper.convertFeatureAccess(access), toList())));
        FeaturesDTO featuresDTO = new FeaturesDTO();
        featuresDTO.setFeatures(featuresByGroup);
        featuresDTO.setUser(authMapper.convertOnlyUser(user));
        return featuresDTO;
    }

    @Transactional(readOnly = true)
    public boolean validateFeatureAccess(RequestContext context, String featureUri, String featureMethod) {
        JPAQuery query = new JPAQuery(entityManager);
        QFeatureAccess featureAccess = QFeatureAccess.featureAccess;
        long count = query.from(featureAccess)
                    .where(featureAccess.featureUrl.eq(featureUri).and(featureAccess.method.eq(featureMethod))).count();
        return count == 0 || findUserByEmail(context, context.getUserId()).getFeatureAccesses().stream()
                .anyMatch(access -> isMatching(access, featureUri, featureMethod));
    }

    private String getFeatureGroup(FeatureAccess featureAccess) {
        int index = featureAccess.getFeatureId().indexOf("_");
        return featureAccess.getFeatureId().substring(0, index);
    }

    private User getUser(RequestContext context, UserDTO userDTO) {
        if (!StringUtils.isEmpty(userDTO.getId())) {
            return userRepository.getOne(userDTO.getId());
        }
        return findUserByEmployeeId(context, userDTO.getEmployeeId());
    }

    private boolean isMatching(FeatureAccess featureAccess, String featureUri, String featureMethod) {
        return featureUri.contains(featureAccess.getFeatureUrl()) && featureAccess.getMethod().equals(featureMethod);
    }
}