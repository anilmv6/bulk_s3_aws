package com.gympro.app.auth.respository;

import com.gympro.app.auth.domain.FeatureAccessEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureAccessEmployeeRepository extends JpaRepository<FeatureAccessEmployee, Long> {
}
