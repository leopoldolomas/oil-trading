package com.leo.oiltrading.rest;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leo.oiltrading.domain.model.OilType;
import com.leo.oiltrading.domain.service.misc.MiscService;
import com.leo.oiltrading.domain.service.oiltype.OilTypeService;

/**
 * Provides endpoints to calculate values suchas revenue yield, price to earnings ratio
 * and inventory index
 * @author Leo Lomas
 *
 */
@RestController
@RequestMapping("/misc")
public class MiscController {
	
	Logger logger = LoggerFactory.getLogger(MiscController.class);
	
	@Autowired
	private OilTypeService oilTypeService;
	
	@Autowired
	private MiscService miscService;
	
	/**
	 * Endpoint used to calculate revenue yield
	 * @param prices Price per oil type in Json format
	 * @return
	 */
	@PostMapping("/revenue-yield")
	public ResponseEntity<String> getRevenueYield(@RequestBody String prices) {
		var pricesMap = buildPricesMap(prices);
		
		Map<OilType, Double> r = null;
		try {
			r = miscService.calculateRevenueYield(pricesMap, oilTypeService.getAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.ok(r.toString());
	}
	
	/**
	 * Endpoint used to calculate price to earnings ratio
	 * @param prices Price per oil type in Json format
	 * @return
	 */
	@PostMapping("/price-earnings-ratio")
	public ResponseEntity<String> getPriceEarningsRatio(@RequestBody String prices) {
		var pricesMap = buildPricesMap(prices);
		
		Map<OilType, Double> r = null;
		try {
			r = miscService.calculatePriceEarningsRatio(pricesMap, oilTypeService.getAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.ok(r.toString());
	}
	
	/**
	 * Endpoint used to calculate inventory index
	 * @param prices Price per oil type in Json format
	 * @return
	 */
	@PostMapping("/inventory-index")
	public ResponseEntity<String> getInventoryIndex(@RequestBody String prices) {
		var pricesMap = buildPricesMap(prices);
		
		double r = -1.0;
		try {
			r = miscService.calculateInventoryIndex(pricesMap, oilTypeService.getAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.ok(Double.toString(r));
	}

	private Map<String, Double> buildPricesMap(String prices) {
		return new Gson().fromJson(prices, new TypeToken<HashMap<String, Double>>(){}.getType());
	}
}
