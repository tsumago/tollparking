package com.amine.tollparking.repository;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ParkingRepositoryTests {

	@Autowired
	private ParkingRepository parkingRepository;

	@Test
	void injectedComponentsAreNotNull() {
		assertThat(parkingRepository).isNotNull();
	}
}
