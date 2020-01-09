package com.leo.oiltrading;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public abstract class AbstractRestTest {
   protected MockMvc mvc;
   @Autowired
   WebApplicationContext webApplicationContext;

   protected void setUp() {
      mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
   }
   
   protected String mapToJson(Object obj) {
	   var gson = new Gson();
	   return gson.toJson(obj);
   }
   
   protected <T> T mapFromJson(String json, Class<T> clazz) {
      var gson = new Gson();
      return gson.fromJson(json, clazz);
   }
}