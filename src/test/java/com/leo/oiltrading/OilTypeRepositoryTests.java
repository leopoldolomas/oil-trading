package com.leo.oiltrading;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.leo.oiltrading.domain.model.OilType;
import com.leo.oiltrading.domain.model.OilType.Type;
import com.leo.oiltrading.domain.repository.InMemOilTypeRepository;

@SpringBootTest
class OilTypeRepositoryTests {
	
	Logger logger = LoggerFactory.getLogger(OilTypeRepositoryTests.class);
	
	@Autowired
	InMemOilTypeRepository repository;

	@Test
	void testInitialValues() {
		assertTrue(repository.contains("AAC"));
		assertTrue(repository.contains("REW"));
		assertTrue(repository.contains("BWO"));
		assertTrue(repository.contains("TIM"));
		assertTrue(repository.contains("QFC"));
	}
	
	@Test
	void testAddOilType() {
		OilType ot = new OilType("XYZ", "XYZ", Type.STANDARD, 50, 0, 324);
		repository.add(ot);
		assertTrue(repository.get("XYZ").get().equals(ot));
	}
	
	@Test
	void testRemoveOilType() {
		OilType ot = new OilType("XYZ", "XYZ", Type.STANDARD, 50, 0, 324);
		repository.add(ot);
		repository.remove("XYZ");
		assertFalse(repository.contains("XYZ"));
	}
	
	@Test
	void testUpdateOilType() {
		var ot = new OilType("AAC", "AAC", Type.PREMIUM, 1,  9, 99);
		repository.update(ot);
		var _ot = repository.get("AAC").get();
		assertTrue(_ot.getType().equals(Type.PREMIUM));
		assertTrue(_ot.getVariableRevenue() == 9);
		assertTrue(_ot.getOilBarrelValue() == 99);
	}

}
