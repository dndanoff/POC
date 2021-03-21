package io.github.dndanoff.repository.impl;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.dndanoff.BaseIntegrationTest;

@RunWith(SpringRunner.class)
@SqlGroup({
	@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/insertTestData.sql"),
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/deleteTestData.sql")
})
public class ShippingItemRepositoryImplIt extends BaseIntegrationTest {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

}
