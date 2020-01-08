package com.leo.oiltrading.domain.service;

import com.leo.oiltrading.domain.repository.ReadOnlyRepository;

public abstract class ReadOnlyBaseService<TE, T> {
	
	@SuppressWarnings("unused")
	private ReadOnlyRepository<TE, T> repository;
	
	public ReadOnlyBaseService(ReadOnlyRepository<TE, T> repository) {
		this.repository = repository;
	}
}
