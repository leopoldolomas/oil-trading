package com.leo.oiltrading;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.leo.oiltrading.domain.model.OilType;
import com.leo.oiltrading.domain.model.OilType.Type;
import com.leo.oiltrading.domain.service.oiltype.OilTypeService;

@SpringBootTest
class OilTypeServiceTests {
	
	Logger logger = LoggerFactory.getLogger(OilTypeServiceTests.class);
	
	@Autowired
	OilTypeService service;

	@Test
	void testInitialValues() throws Exception {
		assertTrue(service.findById("AAC") != null);
		assertTrue(service.findById("REW") != null);
		assertTrue(service.findById("BWO") != null);
		assertTrue(service.findById("TIM") != null);
		assertTrue(service.findById("QFC") != null);
	}
	
	@Test
	void testAddOilType() throws Exception {
		OilType ot = new OilType("XYZ", "XYZ", Type.STANDARD, 50, 0, 324);
		service.add(ot);
		assertTrue(service.findById("XYZ").equals(ot));
	}
	
	@Test
	void testRemoveOilType() throws Exception {
		OilType ot = new OilType("XYZ", "XYZ", Type.STANDARD, 50, 0, 324);
		service.add(ot);
		service.delete("XYZ");
		assertTrue(service.findById("XYZ") == null);
	}
	
	@Test
	void testUpdateOilType() throws Exception {
		var ot = new OilType("AAC", "AAC", Type.PREMIUM, 1,  9, 99);
		service.update(ot);
		var _ot = (OilType)service.findById("AAC");
		assertTrue(_ot.getType().equals(Type.PREMIUM));
		assertTrue(_ot.getVariableRevenue() == 9);
		assertTrue(_ot.getOilBarrelValue() == 99);
	}
}
