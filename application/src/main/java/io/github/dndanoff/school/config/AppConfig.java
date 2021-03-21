package io.github.dndanoff.school.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;

@ConfigurationProperties(prefix = "config")
@Component
@Getter
public class AppConfig {
	
	private Db db = new Db();
	
	@ConfigurationProperties(prefix = "db")
	@Data
	public class Db {
		private String applicationSchema;
	}
}
