package com.leo.oiltrading.domain.model;

public class TransactionVO {
	
	private long timestamp;
	private String oilType;
	private String transactionType;
	private int qty;
	private double price;
	
	public TransactionVO(long timestamp, String oilType, String transactionType, int qty, double price) {
		super();
		setTimestamp(timestamp);
		setOilType(oilType);
		setTransactionType(transactionType);
		setQty(qty);
		setPrice(price);
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getOilType() {
		return oilType;
	}

	public void setOilType(String oilType) {
		this.oilType = oilType;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
