package com.gympro.app.organization.domain;

import com.gympro.app.base.db.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "org_customer")
@Setter
@Getter
public class Customer extends BaseEntity {

    @Column(name = "firstName") private String firstName;

    @Column(name = "lastName") private String lastName;

    @Column(name = "birthDate") private LocalDateTime birthDate;

    @Column(name = "gender") private String gender;

    @Column(name = "image") private String image;

    @Column(name = "martial_status") private String martialStatus;

    @Column(name = "customer_email") private String customerEmail;

    @Column(name = "customer_type") private String customerType;

    @OneToMany(
        mappedBy = "customer",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<Phone> phones = new HashSet<>();

    @OneToMany(
        mappedBy = "customer",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<CustomerAddress> customerAddresses = new HashSet<>();

    @OneToMany(
            mappedBy = "customer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private Set<PosCustomer> posCustomers = new HashSet<>();

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Social social;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Health health;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Work work;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CustomerSettings customerSettings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receptionist_id")
    private Receptionist managedBy;

    @OneToMany(
        mappedBy = "customer",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<Service> services = new HashSet<>();

    @OneToMany(
            mappedBy = "customer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private Set<Message> messages = new HashSet<>();

    //TODO

    //Trainers to be added

    public Customer(Long id) {
        super(id);
    }

    public Customer() {
    }

    public void addPosCustomer(PosCustomer posCustomer) {
        posCustomers.add(posCustomer);
        posCustomer.setCustomer(this);
    }

    public void removePosCustomer(PosCustomer posCustomer) {
        posCustomers.remove(posCustomer);
        posCustomer.setCustomer(null);
    }

    public void addMessage(Message message) {
        messages.add(message);
        message.setCustomer(this);
    }
    public void removeMessage(Message message) {
        messages.remove(message);
        message.setCustomer(null);
    }

    public void addPhone(Phone phone) {
        phones.add(phone);
        phone.setCustomer(this);
    }

    public void addAddress(CustomerAddress customerAddress) {
        customerAddresses.add(customerAddress);
        customerAddress.setCustomer(this);
    }

}
