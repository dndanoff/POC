package io.github.dndanoff.school.application.adapters.in.web;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RequestResponseLoggingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		MDC.clear();
        MDC.put("CORRELATION_ID", UUID.randomUUID().toString());
		log.info("Logging Request:  METHOD={}, URL={}", req.getMethod(), req.getRequestURI());
		chain.doFilter(request, response);
		log.info("Logging Response: Status={}", res.getStatus());
		MDC.clear();
	}
}
