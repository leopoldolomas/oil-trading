package com.leo.oiltrading.domain.service.misc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.leo.oiltrading.domain.model.OilType;
import com.leo.oiltrading.domain.model.OilType.Type;

@Service("miscService")
public class MiscServiceImpl implements MiscService {

	@Override
	public Map<OilType, Double> calculateRevenueYield(Map<String, Double> prices, Collection<OilType> oilTypes) {
		var r = new HashMap<OilType, Double>();
		
		oilTypes.stream().forEach(ot -> {
			double price = prices.get(ot.getId());
			double revenue = calculateRevenue(ot);
			double revenueYield = revenue / price;
			r.put(ot, revenueYield);
		});
		
		return r;
	}

	@Override
	public Map<OilType, Double> calculatePriceEarningsRatio(Map<String, Double> prices, Collection<OilType> oilTypes) {
		var r = new HashMap<OilType, Double>();
		
		oilTypes.stream().forEach(ot -> {
			double price = prices.get(ot.getId());
			double revenue = calculateRevenue(ot);
			double priceEarningsRatio = price / revenue;
			r.put(ot, priceEarningsRatio);
		});
		
		return r;
	}

	@Override
	public Double calculateInventoryIndex(Map<String, Double> prices) {
		var sum = prices.values().stream().mapToDouble(d -> d).sum();
		return Math.pow(sum, 1.0 / prices.size()); //n-th root of sum
	}
	
	private double calculateRevenue(OilType ot) {		
		return ot.getType() == Type.STANDARD ? ot.getFixedRevenue() : ((ot.getVariableRevenue() / 100.0) * ot.getOilBarrelValue());
	}
}
