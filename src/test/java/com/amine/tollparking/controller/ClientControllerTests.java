package com.amine.tollparking.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ClientControllerTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void clientEdition_WhenClientNotFound() {
		try {
			mockMvc.perform(get("/client/{username}", "test")
					.contentType("application/json"))
			        .andExpect(status().isNotFound())
			        .andExpect(jsonPath("$.message").value("Client with username test not found"));
		} catch (Exception e) {
			fail("Get inexistant client test failed with an Exception");
		}
	}
	
	@Test
	public void clientEdition_WhenClientFound() {
		try {
			mockMvc.perform(get("/client/{username}", "tsumago")
					.contentType("application/json"))
			        .andExpect(status().isFound());
		} catch (Exception e) {
			fail("Get existant client test failed with an Exception");
		}
	}
	
	@Test
	public void clientDelete_WhenClientNotFound() {
		try {
			mockMvc.perform(delete("/client/{username}", "test")
					.contentType("application/json"))
			        .andExpect(status().isNotFound());
		} catch (Exception e) {
			fail("Delete inexistant client test failed with an Exception");
		}
	}
	
	@Test
	public void clientDelete_WhenClientFound() {
		try {
			mockMvc.perform(delete("/client/{username}", "demers")
					.contentType("application/json"))
			        .andExpect(status().isOk());
		} catch (Exception e) {
			fail("Delete existant client test failed with an Exception");
		}
	}
}
