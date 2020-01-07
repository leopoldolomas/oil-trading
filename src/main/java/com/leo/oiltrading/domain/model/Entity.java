package com.leo.oiltrading.domain.model;

public abstract class Entity<T> {
	T id;
	String name;
	
	public abstract void setId(T id);
	public abstract void setName(String name);
	
	public Entity(T id, String name) {
		super();
		setId(id);
		setName(name);
	}

	public T getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}