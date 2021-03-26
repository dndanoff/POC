package io.github.dndanoff.school.config.rest;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("io.github.dndanoff"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
				.securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(authScheme()));
    }

	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "School REST API", 
	      "Some custom description of API.", 
	      "API TOS", 
	      "Terms of service", 
	      new Contact("John Doe", "www.example.com", "myeaddress@company.com"), 
	      "License of API", "API license URL", new ArrayList<>());
	}
	
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(authReference()))
                .forPaths(PathSelectors.any())
                .build();
    }

    private SecurityScheme authScheme() {
        return new BasicAuth("basicAuth");
    }

    private SecurityReference authReference() {
        return new SecurityReference("basicAuth", new AuthorizationScope[0]);
    }
}
