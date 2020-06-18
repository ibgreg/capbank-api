package com.ibgregorio.capbank.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ibgregorio.capbank.services.MockService;

@Configuration
public class MockConfig {

	@Autowired
	private MockService mockService;
	
	@Bean
	public boolean loadDatabase() throws ParseException {
		mockService.loadMockDatabase();
		
		return true;
	}
}
