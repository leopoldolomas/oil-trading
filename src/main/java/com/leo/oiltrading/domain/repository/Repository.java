package com.leo.oiltrading.domain.repository;

public interface Repository<TE, T> extends ReadOnlyRepository<TE, T> {
	public void add(TE entity);
	public void remove(T id);
	public void update(TE entity);
}
