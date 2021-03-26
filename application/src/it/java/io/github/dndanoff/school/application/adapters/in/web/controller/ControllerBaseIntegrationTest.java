package io.github.dndanoff.school.application.adapters.in.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import io.github.dndanoff.BaseIntegrationTest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class ControllerBaseIntegrationTest extends BaseIntegrationTest {
	
	@LocalServerPort
	private int port;

	@Autowired
	protected TestRestTemplate restTemplate;
	protected String baseUrl;
    
    @BeforeEach
    public void setup() {
    	baseUrl = "http://localhost:" + port + "/";
    }
}
