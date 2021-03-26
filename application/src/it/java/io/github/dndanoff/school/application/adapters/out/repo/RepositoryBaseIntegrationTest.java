package io.github.dndanoff.school.application.adapters.out.repo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.jdbc.support.JdbcUtils;

import io.github.dndanoff.BaseIntegrationTest;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public abstract class RepositoryBaseIntegrationTest extends BaseIntegrationTest {
	
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    
    @Autowired
    protected DataSource dataSource;
    
    @AfterEach
    public void cleanup() throws SQLException {
    	Connection conn = dataSource.getConnection();
        ScriptUtils.executeSqlScript(conn, new ClassPathResource(
                "db/deleteTestData.sql"));
        JdbcUtils.closeConnection(conn);
    }
}
