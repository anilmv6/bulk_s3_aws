package com.gympro.app.organization.service;


import com.gympro.app.base.db.domain.BaseEntity;
import com.gympro.app.base.exception.ResourceNotFoundException;
import com.gympro.app.organization.repository.XRepository;

import java.util.NoSuchElementException;


public abstract class AbstractService<E extends BaseEntity> {


  /**
   *
   * @param entity
   * @return
   * @throws ResourceNotFoundException
   */
  public E insertResource(E entity) throws ResourceNotFoundException {
    getRepository().save(entity);
    return findResourceById(entity.getId());
  }

  /**
   *
   * @param id
   * @return
   * @throws ResourceNotFoundException
   */
  public E findResourceById(Long id) throws ResourceNotFoundException {
    try {
      return getRepository().findById(id).get();
    } catch (NoSuchElementException e) {
      throw new ResourceNotFoundException(e, getEntityName());
    }
  }



  /**
   *
   * @param id
   * @return
   * @throws ResourceNotFoundException
   */
  public E deleteResource(Long id) throws ResourceNotFoundException {
    E e = findResourceById(id);
    getRepository().deleteById(id);
    return e;
  }

  protected abstract String getEntityName();

  public abstract XRepository<E> getRepository();

}
