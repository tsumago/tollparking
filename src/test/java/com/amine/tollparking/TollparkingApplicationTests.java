package com.amine.tollparking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class TollparkingApplicationTests {

	@Test
	void contextLoads() {
	}

}
