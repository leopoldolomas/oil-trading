package com.leo.oiltrading.domain.model;

public class OilType extends BaseEntity<String> {
	
	public static enum Type { STANDARD, PREMIUM };
	
	private Type type;
	private int fixedRevenue;
	private int variableRevenue;
	private int oilBarrelValue;

	public OilType(String id, String name, Type type, int fixedRevenue, int variableRevenue, int oilBarrelValue) {
		super(id, name);
		setType(type);
		setFixedRevenue(fixedRevenue);
		setVariableRevenue(variableRevenue);
		setOilBarrelValue(oilBarrelValue);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof OilType)) {
			return false;
		}
		
		var other = (OilType) obj;
		
		return this.getId().equals(other.getId()) &&
				this.getName().equals(other.getName());
	}
	
	@Override
	public String toString() {
		return id;
	}

	public Type getType() {
		return type;
	}

	public int getFixedRevenue() {
		return fixedRevenue;
	}

	public int getVariableRevenue() {
		return variableRevenue;
	}

	public int getOilBarrelValue() {
		return oilBarrelValue;
	}

	private void setType(Type type) {
		this.type = type;
	}

	private void setFixedRevenue(int fixedRevenue) {
		this.fixedRevenue = fixedRevenue;
	}

	private void setVariableRevenue(int variableRevenue) {
		this.variableRevenue = variableRevenue;
	}

	private void setOilBarrelValue(int oilBarrelValue) {
		this.oilBarrelValue = oilBarrelValue;
	}
}
