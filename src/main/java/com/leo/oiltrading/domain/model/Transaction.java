package com.leo.oiltrading.domain.model;

public class Transaction extends BaseEntity<Long> {
	
	public static enum Type { BUY, SELL };
	private Type type;
	private int qty;
	private double price;
	
	public Transaction(Long id, String name, Type type, int qty, double price) {
		super(id, name);
		setType(type);
		setQty(qty);
		setPrice(price);
	}

	public Type getType() {
		return type;
	}

	public int getQty() {
		return qty;
	}

	public double getPrice() {
		return price;
	}

	private void setType(Type type) {
		this.type = type;
	}

	private void setQty(int qty) {
		this.qty = qty;
	}

	private void setPrice(double price) {
		this.price = price;
	}
}
