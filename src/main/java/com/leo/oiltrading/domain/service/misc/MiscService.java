package com.leo.oiltrading.domain.service.misc;

import java.util.Collection;
import java.util.Map;

import com.leo.oiltrading.domain.model.OilType;

public interface MiscService {
	Map<OilType, Double> calculateRevenueYield(Map<String, Double> prices, Collection<OilType> oilTypes);
	Map<OilType, Double> calculatePriceEarningsRatio(Map<String, Double> prices, Collection<OilType> oilTypes);
	Double calculateInventoryIndex(Map<String, Double> prices, Collection<OilType> oilTypes);
}
