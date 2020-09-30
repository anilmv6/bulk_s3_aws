package com.gympro.app.auth.respository;

import com.gympro.app.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
