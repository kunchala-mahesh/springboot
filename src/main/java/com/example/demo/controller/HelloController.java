package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Spring Boot CI/CD Demo Application!";
    }

    @GetMapping("/api/hello")
    public Map<String, String> sayHello(@RequestParam(defaultValue = "Jenkins") String name) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, " + name + "! CI/CD pipeline build was successful.");
        response.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return response;
    }

    @GetMapping("/api/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("app", "spring-boot-jenkins-practice");
        return ResponseEntity.ok(status);
    }
}
