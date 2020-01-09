package com.leo.oiltrading;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MiscControllerTest extends AbstractRestTest {
	
	private final static String inputJson = "{ \"AAC\" : 1,\"REW\" : 2,\"BWO\" : 3,\"TIM\" : 4,\"QFC\" : 5 }";

	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}

	// (at least one end-to-end scenario using JUnit)
	@Test
	public void testRevenueYield() throws Exception {
		String uri = "/misc/revenue-yield/";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		var pricesMap = buildPricesMap(content);
		assertTrue(pricesMap.get("AAC") == 1.0);
		assertTrue(pricesMap.get("REW") == 3.5);
		assertTrue(pricesMap.get("QFC") == 4.4);
		assertTrue(pricesMap.get("TIM") == 1.9425000000000001);
		assertTrue(pricesMap.get("BWO") == 5.666666666666667);
	}
	
	// TODO move to a Util class so it can be reused by other classes
	private Map<String, Double> buildPricesMap(String prices) {
		return new Gson().fromJson(prices, new TypeToken<HashMap<String, Double>>(){}.getType());
	}
}
