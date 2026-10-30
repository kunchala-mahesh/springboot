package com.example.demo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should return home welcome text")
    void shouldReturnHomeText() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to Spring Boot CI/CD Demo Application!"));
    }

    @Test
    @DisplayName("Should return customized greeting for Jenkins")
    void shouldReturnGreeting() throws Exception {
        mockMvc.perform(get("/api/hello").param("name", "Engineer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, Engineer! CI/CD pipeline build was successful."))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    @DisplayName("Should return UP status for health check")
    void shouldReturnHealthStatus() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.app").value("spring-boot-jenkins-practice"));
    }
}
