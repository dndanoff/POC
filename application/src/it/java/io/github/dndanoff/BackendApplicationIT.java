package io.github.dndanoff;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BackendApplicationIT extends BaseIntegrationTest {
	
	@Test
	void contextLoads() {
	}

}
