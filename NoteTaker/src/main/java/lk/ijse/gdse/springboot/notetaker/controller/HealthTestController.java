package lk.ijse.gdse.springboot.notetaker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/healthTest") //http://localhost:8080/notetaker/api/v1/healthTest
public class HealthTestController {
    @GetMapping
    public String healthCheck() {
        return "Note taker is running";
    }
}
