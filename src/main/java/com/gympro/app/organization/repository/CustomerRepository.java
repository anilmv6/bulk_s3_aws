package com.gympro.app.organization.repository;

import com.gympro.app.organization.domain.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends XRepository<Customer> {
}
