package com.gympro.app.auth.domain;

import com.gympro.app.base.db.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AUTH_ADDRESS")
public class Address extends BaseEntity {

    @Column(name = "ADDRESS_LINE_1", nullable = false)
    private String addressLine1;

    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;

    @Column(name = "PIN_CODE")
    private long pinCode;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "COUNTY")
    private String county;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    @Column(name = "ISO_COUNTRY", nullable = false)
    private String isoCountry;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
    private User user;

    public Address(Long id) {
        super(id);
    }

    public Address() {
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public long getPinCode() {
        return pinCode;
    }

    public void setPinCode(long pinCode) {
        this.pinCode = pinCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIsoCountry() {
        return isoCountry;
    }

    public void setIsoCountry(String isoCountry) {
        this.isoCountry = isoCountry;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", pinCode=" + pinCode +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", county='" + county + '\'' +
                ", country='" + country + '\'' +
                ", isoCountry='" + isoCountry + '\'' +
                ", type=" + type +
                ", user=" + user +
                '}';
    }
}
