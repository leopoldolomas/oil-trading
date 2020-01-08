package com.leo.oiltrading.domain.service;

import java.util.Collection;

import com.leo.oiltrading.domain.model.Entity;
import com.leo.oiltrading.domain.model.OilType;

public interface OilTypeService {
	  public void add(OilType oilType) throws Exception;
	  public void update(OilType oilType) throws Exception;
	  public void delete(String id) throws Exception;
	  @SuppressWarnings("rawtypes")
	  public Entity findById(String id) throws Exception;
	  public Collection<OilType> findByName(String name) throws Exception;
}
