package com.leo.oiltrading.rest;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leo.oiltrading.domain.model.OilType;
import com.leo.oiltrading.domain.service.misc.MiscService;
import com.leo.oiltrading.domain.service.oiltype.OilTypeService;

@RestController
public class OilTypeController {
	
	Logger logger = LoggerFactory.getLogger(OilTypeController.class);
	
	@Autowired
	private OilTypeService oilTypeService;
	
	@Autowired
	private MiscService miscService;
	
	@PostMapping("/revenue-yield/")
	public ResponseEntity<String> getRevenueYield(@RequestBody String prices) {
		var pricesMap = buildPricesMap(prices);
		Map<OilType, Double> r = null;
		try {
			r = miscService.calculateRevenueYield(pricesMap, oilTypeService.getAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok(r.toString());
	}

	private Map<String, Double> buildPricesMap(String prices) {
		return new Gson().fromJson(prices, new TypeToken<HashMap<String, Double>>(){}.getType());
	}

}
