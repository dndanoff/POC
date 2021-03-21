package io.github.dndanoff.school.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(value="io.github.dndanoff.school.application.adapters.out", considerNestedRepositories = true)
@EntityScan("io.github.dndanoff.school.domain.model")
@EnableJpaAuditing
public class PersistenceConfig {
    
}
