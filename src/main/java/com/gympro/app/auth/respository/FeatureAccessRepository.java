package com.gympro.app.auth.respository;

import com.gympro.app.auth.domain.FeatureAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureAccessRepository extends JpaRepository<FeatureAccess, Long> {
}
