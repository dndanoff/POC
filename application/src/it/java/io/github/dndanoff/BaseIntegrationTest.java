package io.github.dndanoff;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;

@ActiveProfiles("itest")
public class BaseIntegrationTest {

	static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13-alpine")
			.withDatabaseName("public").withUsername("itest_school_user").withPassword("secret");

	@BeforeAll
	public static void init() {
		postgreSQLContainer.start();
		System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
		System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
		System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
		System.setProperty("config.db.application_schema", postgreSQLContainer.getDatabaseName());
	}

}
