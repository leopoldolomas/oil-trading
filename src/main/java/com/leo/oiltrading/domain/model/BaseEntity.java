package com.leo.oiltrading.domain.model;

public abstract class BaseEntity<T> extends Entity<T> {
	
	boolean isModified;

	public BaseEntity(T id, String name) {
		super(id, name);
		isModified = false;
	}
	
	public boolean isModified() {
		return isModified;
	}
	
	@Override
	public void setId(T id) {
		this.id = id;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
}
