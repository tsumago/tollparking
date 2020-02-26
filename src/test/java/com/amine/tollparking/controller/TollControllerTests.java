package com.amine.tollparking.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@TestMethodOrder(OrderAnnotation.class)
public class TollControllerTests {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Order(1)
	public void carEnter_WhenCarNotFound() {
		try {
			mockMvc.perform(get("/toll/enter/{carPlate}-{parkingId}", "TEST", "1")
					.contentType("application/json"))
			        .andExpect(status().isNotFound())
			        .andExpect(jsonPath("$.message").value("Car with plate number TEST is not registered"));
		} catch (Exception e) {
			fail("Enter with a not registered car failed with an exception");
		}
	}
	
	@Test
	@Order(2)
	public void carEnter_WhenSpotNotFound() {
		try {
			mockMvc.perform(get("/toll/enter/{carPlate}-{parkingId}", "H3545V", "1")
					.contentType("application/json"))
			        .andExpect(status().isForbidden())
			        .andExpect(jsonPath("$.message").value("No Available spot found"));
		} catch (Exception e) {
			fail("Enter with no available spot test failed with an Exception");
		}
	}
	
	@Test
	@Order(3)
	public void carEnter_WhenHappyPath() {
		try {
			mockMvc.perform(get("/toll/enter/{carPlate}-{parkingId}", "Z123DF", "1")
					.contentType("application/json"))
			        .andExpect(status().isOk());
		} catch (Exception e) {
			fail("Enter happy path test failed with an Exception");
		}
	}
	
	@Test
	@Order(4)
	public void carLeaving_WhenHappyPath() {
		try {
			mockMvc.perform(get("/toll/leave/{carPlate}", "Z123DF")
					.contentType("application/json"))
			        .andExpect(status().isOk())
			        .andExpect(jsonPath("$.balance").isNotEmpty());
		} catch (Exception e) {
			fail("Leave happy path test failed with an Exception");
		}
	}
	
	@Test()
	@Order(5)
	public void carLeaving_WhenBalanceIsNegative() {
		try {
			mockMvc.perform(get("/toll/enter/{carPlate}-{parkingId}", "J2F4GR", "1"));
			mockMvc.perform(get("/toll/leave/{carPlate}", "J2F4GR")
					.contentType("application/json"))
			        .andExpect(status().isPaymentRequired())
			        .andExpect(jsonPath("$.balance").isNotEmpty());
		} catch (Exception e) {
			fail("Leave with negative balance test failed with an Exception");
		}
	}
}
