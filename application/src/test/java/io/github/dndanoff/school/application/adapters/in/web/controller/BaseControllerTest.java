package io.github.dndanoff.school.application.adapters.in.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles(profiles = "test")
public abstract class BaseControllerTest {
	protected static final String USER_DUMMY = "user";
	protected static final String PASSWORD_DUMMY = "password";

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper mapper;
    
    protected abstract String getBaseUrl();
    
    @Test
    void controller_ShouldReturn401_WhenNotAuthorized() throws Exception {
        mockMvc.perform(get(getBaseUrl()))
                .andExpect(status().is4xxClientError());
    }
}
