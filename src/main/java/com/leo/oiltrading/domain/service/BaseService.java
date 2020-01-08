package com.leo.oiltrading.domain.service;

import java.util.Collection;
import com.leo.oiltrading.domain.repository.Repository;

public abstract class BaseService<TE, T> extends ReadOnlyBaseService<TE, T> {
	Repository<TE, T> _repository;

	BaseService(Repository<TE, T> repository) {
		super(repository);
		_repository = repository;
	}
	
	public void add(TE entity) {
		_repository.add(entity);
	}
	
	public Collection<TE> getAll() {
		return _repository.getAll();
	}
}
