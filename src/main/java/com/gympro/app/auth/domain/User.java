package com.gympro.app.auth.domain;

import com.gympro.app.base.db.domain.PosCompanyEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "AUTH_USER")
public class User extends PosCompanyEntity implements UserDetails {


    @Column(name = "EMAIL", unique = true)
    @NotNull
    private String email;

    @Column(name = "PASSWORD")
    @NotNull
    private String password;

    public String getEmployeeId() {
        return employeeId;
    }

    @Column(name = "EMPLOYEE_ID", unique = true)
    @NotNull
    private String employeeId;

    @Column(name = "DESIGNATION")
    @NotNull
    private String designation;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.MERGE
    )
    private Set<FeatureAccessEmployee> featureAccessEmployees = new HashSet<>();

    public User(Long id) {
        super(id);
    }

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

    return Stream.of(designation)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getEnabled();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setUser(null);
    }

    public void addFeatureAccessEmployee(FeatureAccessEmployee featureAccessEmployee) {
        featureAccessEmployees.add(featureAccessEmployee);
        featureAccessEmployee.setUser(this);
    }

    public void removeFeatureAccessEmployee(FeatureAccessEmployee featureAccessEmployee) {
        featureAccessEmployees.remove(featureAccessEmployee);
        featureAccessEmployee.setUser(null);
    }

    public Set<Address> getAddresses() {
        return addresses;
    }


    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Set<FeatureAccessEmployee> getFeatureAccessEmployees() {
        return featureAccessEmployees;
    }

    public Set<FeatureAccess> getFeatureAccesses() {
        System.out.println(featureAccessEmployees);
        return getFeatureAccessEmployees().stream().map(FeatureAccessEmployee::getFeature).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
