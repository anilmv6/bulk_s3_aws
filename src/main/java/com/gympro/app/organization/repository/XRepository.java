package com.gympro.app.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface XRepository<E> extends JpaRepository<E,Long> {

}
