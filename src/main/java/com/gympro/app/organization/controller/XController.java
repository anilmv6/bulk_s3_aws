package com.gympro.app.organization.controller;

import com.gympro.app.base.db.domain.BaseEntity;
import com.gympro.app.base.exception.ResourceNotFoundException;
import com.gympro.app.organization.service.AbstractService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class XController<E extends BaseEntity> {

  public static final Logger LOGGER = LogManager.getLogger(XController.class);

  public static final String GET_RESOURCE_BY_ID = "GET {} resource by {} Invoked";
  public static final String CREATE_RESOURCE = "CREATE {} resource Invoked";
  public static final String PUT_RESOURCE = "PUT {} resource Invoked";
  public static final String DELETE_RESOURCE = "DELETE {} resource Invoked";
  public static final String GET_RESOURCES = "GET {} resources Invoked";
  public static final String INTERCEPT_RESOURCES = "INTERCEPT {} Invoked";


  /**
   * 
   * @param entity
   * @return
   * @throws ResourceNotFoundException
   */

  public E insertResource(final E entity) throws ResourceNotFoundException {
    return getService().insertResource(entity);
  }


  /**
   * 
   * @param id
   * @return
   * @throws ResourceNotFoundException
   */
  public E findResourceById(final Long id) throws ResourceNotFoundException {
    return getService().findResourceById(id);
  }

  /**
   * 
   * @param id
   * @return
   * @throws ResourceNotFoundException
   */
  public E deleteResource(final Long id) throws ResourceNotFoundException {
    return getService().deleteResource(id);
  }

  public abstract AbstractService<E> getService();
}
