package com.leo.oiltrading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class TradingControllerTests extends AbstractRestTest {
	private final static String inputJson = "{\"timestamp\":1578535189975,\"oilType\":\"AAC\",\"transactionType\":\"BUY\",\"accountId\":1234567890,\"qty\":12,\"price\":25}";

	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void testTrade() throws Exception {
		// record transaction
		String uri = "/trading/trade/";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		assertTrue(mvcResult.getResponse().getContentAsString().equalsIgnoreCase("Transaction registered successfully"));
		
		// get volume weighted oil price
		uri = "/trading/vw-oil-price";
		mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		assertTrue(mvcResult.getResponse().getContentAsString().equalsIgnoreCase("25.0"));
	}
}
