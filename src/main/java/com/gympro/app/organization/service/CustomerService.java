package com.gympro.app.organization.service;

import com.gympro.app.base.db.EntityFactory;
import com.gympro.app.base.db.QueryFactory;
import com.gympro.app.base.rest.PageDetails;
import com.gympro.app.base.rest.PageDetails.SortType;
import com.gympro.app.base.type.request.RequestContext;
import com.gympro.app.organization.domain.Customer;
import com.gympro.app.organization.domain.CustomerAddress;
import com.gympro.app.organization.domain.CustomerSettings;
import com.gympro.app.organization.domain.Health;
import com.gympro.app.organization.domain.Message;
import com.gympro.app.organization.domain.Payment;
import com.gympro.app.organization.domain.Phone;
import com.gympro.app.organization.domain.PointOfSale;
import com.gympro.app.organization.domain.PosCustomer;
import com.gympro.app.organization.domain.QCustomer;
import com.gympro.app.organization.domain.QMessage;
import com.gympro.app.organization.domain.Social;
import com.gympro.app.organization.domain.Work;
import com.gympro.app.organization.dto.CustomerAddressDTO;
import com.gympro.app.organization.dto.CustomerDTO;
import com.gympro.app.organization.dto.CustomerSettingsDTO;
import com.gympro.app.organization.dto.HealthDTO;
import com.gympro.app.organization.dto.MessageDTO;
import com.gympro.app.organization.dto.PaymentDTO;
import com.gympro.app.organization.dto.PhoneDTO;
import com.gympro.app.organization.dto.SocialDTO;
import com.gympro.app.organization.dto.WorkDTO;
import com.gympro.app.organization.enums.EnumCustomerType;
import com.gympro.app.organization.repository.CustomerRepository;
import com.gympro.app.organization.repository.EmployeeRepository;
import com.gympro.app.organization.repository.MessageRepository;
import com.gympro.app.organization.repository.XRepository;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.support.Expressions;
import com.mysema.query.types.Order;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Path;
import com.mysema.query.types.Projections;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CustomerService extends AbstractService<Customer> {

    private final CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private MessageRepository messageRepository;
    private EmployeeService employeeService;
    private CompanyService companyService;
    private PointOfSaleService pointOfSaleService;

    private EntityFactory entityFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public CustomerService(EntityFactory
        entityFactory, CustomerRepository customerRepository, EmployeeRepository employeeRepository,
        MessageRepository messageRepository,
        EmployeeService employeeService,
        CompanyService companyService,
        PointOfSaleService pointOfSaleService) {
        this.entityFactory = entityFactory;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.messageRepository = messageRepository;
        this.employeeService = employeeService;
        this.companyService = companyService;
        this.pointOfSaleService = pointOfSaleService;
    }

    @Override
    protected String getEntityName() {
        return Customer.class.getSimpleName();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Customer createLead(CustomerDTO customerDTO) {
        RequestContext context = RequestContext.getRequestContext();
        Customer customer = entityFactory.newEntity(Customer.class, customerDTO);
        createPartialCustomer(customer,customerDTO);
        customerDTO.getPhoneDTOs().forEach(phoneDTO -> {
            Phone phone = entityFactory.newEntity(Phone.class, phoneDTO);
            createPhone(phone,phoneDTO);
            customer.addPhone(phone);
        });
        customerDTO.getAddressesDtos().forEach(customerAddressDTO ->   {
            CustomerAddress customerAddress = entityFactory.newEntity(CustomerAddress.class, customerAddressDTO);
            createCustomerAddress(customerAddress,customerAddressDTO);
            customer.addAddress(customerAddress);
        });

        updateCustomerMessage(customerDTO,customer);

        PosCustomer posCustomer = entityFactory.newEntity(PosCustomer.class);
        PointOfSale pointOfSale = pointOfSaleService.findPointOfSaleById(context, Long.parseLong(context.getPosId()));
        posCustomer.setPointOfSale(pointOfSale);
        posCustomer.setCustomer(customer);
        posCustomer.setRequestId(context.getRequestId());
        posCustomer.setCreatedBy(context.getUserId());
        posCustomer.setCreatedDatetime(customerDTO.getRequestDateTime());
        posCustomer.setUpdatedDatetime(customerDTO.getRequestDateTime());
        posCustomer.setUpdatedBy(context.getUserId());
        posCustomer.setEnabled(true);
        pointOfSale.addPosCustomer(posCustomer);
        customer.addPosCustomer(posCustomer);

        if (customerDTO.getCustomerType().equals(EnumCustomerType.CUSTOMER.getDescription())){
            createCustomer(customer,customerDTO);
        }
        return customerRepository.save(customer);
    }

    private void createCustomer(Customer customer, CustomerDTO customerDTO) {
        updateSocial(entityFactory.newEntity(Social.class, customerDTO.getSocial()),customerDTO.getSocial(),customer);
        updateHealth(entityFactory.newEntity(Health.class, customerDTO.getHealth()),customerDTO.getHealth(),customer);
        updatePayment(entityFactory.newEntity(Payment.class, customerDTO.getPayment()),customerDTO.getPayment(),customer);
        updateWork(entityFactory.newEntity(Work.class, customerDTO.getWork()),customerDTO.getWork(),customer);
        updateCustomerSettings(entityFactory.newEntity(CustomerSettings.class, customerDTO.getCustomerSettings())
            ,customerDTO.getCustomerSettings(),customer);

    }

    private void updateCustomerSettings(CustomerSettings customerSettings,
        CustomerSettingsDTO customerSettingsDTO, Customer customer) {
        customerSettings.setRegistrationDate(customerSettingsDTO.getRegistrationDate());
        customerSettings.setJoinedDate(customerSettingsDTO.getJoinedDate());
        customerSettings.setMemberExpirationDate(customerSettingsDTO.getMemberExpirationDate());
        customerSettings.setFreeclasses(customerSettingsDTO.getFreeclasses());
        customer.setCustomerSettings(customerSettings);
    }

    private void updateWork(Work work, WorkDTO workDTO, Customer customer) {
        work.setCompany(workDTO.getCompany());
        work.setCompanyEmail(workDTO.getCompanyEmail());
        customer.setWork(work);
    }

    private void updatePayment(Payment payment, PaymentDTO paymentDTO, Customer customer) {
        payment.setAmountPending(paymentDTO.getAmountPending());
        payment.setAmountPaid(paymentDTO.getAmountPaid());
        payment.setFullAmountPaid(paymentDTO.getFullAmountPaid());
        payment.setTotalAmountToBePaid(paymentDTO.getTotalAmountToBePaid());
        payment.setPartPayment(paymentDTO.getPartPayment());
        customer.setPayment(payment);
    }

    private void updateHealth(Health health, HealthDTO healthDTO, Customer customer) {
        health.setAllergies(healthDTO.getAllergies());
        health.setDietPlan(healthDTO.getAllergies());
        health.setHealthIssue(healthDTO.getHealthIssue());
        customer.setHealth(health);
    }

    private void updateSocial(Social social, SocialDTO socialDTO, Customer customer) {
        social.setGoogle(socialDTO.getGoogle());
        social.setFacebook(socialDTO.getFacebook());
        social.setTwitter(socialDTO.getTwitter());
        customer.setSocial(social);
    }



    private void updateCustomerMessage(CustomerDTO customerDTO, Customer customer) {
        if (!CollectionUtils.isEmpty(customerDTO.getMessages())) {
            customerDTO.getMessages().forEach(message -> {
                Message messageEntity = entityFactory.newEntity(Message.class, message);
                messageEntity.setMessageContent(message.getMessage().getBytes());
                customer.addMessage(messageEntity);
            });
        }
    }

    private void createCustomerAddress(CustomerAddress customerAddress,
        CustomerAddressDTO customerAddressDTO) {
        customerAddress.setAddressType(customerAddressDTO.getAddressType());
        customerAddress.setLine1(customerAddressDTO.getLine1());
        customerAddress.setCity(customerAddressDTO.getCity());
        customerAddress.setState(customerAddressDTO.getState());
        customerAddress.setCountry(customerAddressDTO.getCountry());
        customerAddress.setPostalCode(customerAddressDTO.getPostalCode());
        customerAddress.setPreferred(customerAddressDTO.getPreferred());
    }

    private void createPhone(Phone phone, PhoneDTO phoneDTO) {
        phone.setPhoneNumber(phoneDTO.getPhoneNumber());
        phone.setExtension(phoneDTO.getExtension());
        phone.setPhoneType(phoneDTO.getPhoneType());
        phone.setPreferred(phoneDTO.getPreferred());
    }

    private void createPartialCustomer(Customer customer, CustomerDTO customerDTO) {
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setBirthDate(customerDTO.getBirthDate());
        customer.setCustomerEmail(customerDTO.getCustomerEmail());
        customer.setCustomerType(customerDTO.getCustomerType());
        customer.setGender(customerDTO.getGender());
        customer.setImage(customer.getImage());
        customer.setMartialStatus(customerDTO.getMartialStatus());
        customer.setManagedBy(employeeService.findReceptionistByEmployeeId(customerDTO.getManagedBy()));
    }

    @Transactional(readOnly = true)
    public List<CustomerDTO> listAllLeadsForCurrentEmployee(RequestContext context, PageDetails pageDetails) {
        QCustomer qCustomer = QCustomer.customer;
        JPAQuery query = QueryFactory.createJPAQuery(entityManager, qCustomer, Customer.class);
         JPAQuery intermediateQuery = query.where(qCustomer.managedBy.employeeEmail.eq(context.getUserId())
                .and(qCustomer.customerType.eq("lead"))
                .and(qCustomer.isEnabled.eq(true)));
        List<CustomerDTO> list = applyPagination(intermediateQuery, pageDetails)
                .list(Projections.bean(CustomerDTO.class, qCustomer.firstName, qCustomer.lastName, qCustomer.customerEmail, qCustomer.id));
        return list;
    }

    @Transactional
    public Message addMessage(MessageDTO messageDTO) {
        Message message = entityFactory.newEntity(Message.class, messageDTO);
        message.setMessageContent(messageDTO.getMessage().getBytes());
        message.setCustomer(customerRepository.getOne(messageDTO.getCustomerId()));
        message = messageRepository.save(message);
        return message;
    }

    @Transactional
    public Message updateMessage(MessageDTO messageDTO) {
        Message message = messageRepository.getOne(messageDTO.getId());
        message.setMessageContent(messageDTO.getMessage().getBytes());
        message = messageRepository.save(message);
        return message;
    }

    @Transactional
    public Message deleteMessage(MessageDTO messageDTO) {
        Message message = messageRepository.getOne(messageDTO.getId());
        message.setEnabled(false);
        return messageRepository.save(message);
    }

    @Transactional(readOnly = true)
    public List<Message> findMessagesByCustomer(MessageDTO messageDTO) {
        QMessage qMessage = QMessage.message;
        JPAQuery query = QueryFactory.createJPAQuery(entityManager, qMessage, Message.class);
        List<Message> list = query.where(qMessage.customer.id.eq(messageDTO.getCustomerId())).list(qMessage);
        return list;
    }


    @Override
    public XRepository<Customer> getRepository() {
        return this.customerRepository;
    }


    private JPAQuery applyPagination(JPAQuery intermediateQuery, PageDetails pageDetails) {
        if (pageDetails.getOffset() != null && pageDetails.getLimit() != null) {
            intermediateQuery = intermediateQuery.offset(pageDetails.getOffset()).limit(pageDetails.getLimit());
        }
        if (!SortType.NONE.equals(pageDetails.getSort()) && !StringUtils.isEmpty(pageDetails.getSortBy())) {
            Order order = pageDetails.getSort().equals(SortType.ASC) ? Order.ASC : Order.DESC;
            intermediateQuery = intermediateQuery.orderBy(getSortedColumn(order, pageDetails.getSortBy()));
        }
        return intermediateQuery;
    }

    private OrderSpecifier<?> getSortedColumn(Order order, String fieldName){
        Path<String> fieldPath = Expressions.path(String.class, QCustomer.customer, fieldName);
        return new OrderSpecifier(order, fieldPath);
    }

    @Transactional(readOnly = true)
    public List<Customer> findByCustomerId(List<Long> customerIds) {
    	 QCustomer qCustomer = QCustomer.customer;
    	 JPAQuery query = QueryFactory.createJPAQuery(entityManager, qCustomer, Customer.class);
         List<Customer> list = query.where(qCustomer.customer.id.in(customerIds)).list(qCustomer);
         return list;
    }

}

