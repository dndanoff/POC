package io.github.dndanoff;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.actuate.autoconfigure.metrics.orm.jpa.HibernateMetricsAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class BackendApplicationTests {
	
	@MockBean
	HibernateMetricsAutoConfiguration hm;
	@MockBean
	EntityManagerFactory ef;
    @MockBean
    DataSource ds;
	
	//@Test
	void contextLoads() {
	}

}
