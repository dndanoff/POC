package io.github.dndanoff.school.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.cors()
	        .and()
	        .csrf().disable()
        	.httpBasic()
        	.and()
            .authorizeRequests()
            .mvcMatchers("/swagger-ui.html").permitAll()
            .antMatchers("/swagger-resources/**","/webjars/springfox-swagger-ui/**", "/v2/api-docs").permitAll()
            .antMatchers("/actuator/**").permitAll()
            .anyRequest().authenticated();
    }
}
