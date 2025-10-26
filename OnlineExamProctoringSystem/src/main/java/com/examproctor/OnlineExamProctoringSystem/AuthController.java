package com.examproctor.OnlineExamProctoringSystem;


import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    // Simple in-memory user store (username to password)
    private Map<String, String> userStore = new HashMap<>();

    // In-memory cheat alert logs
    private List<CheatingAlert> alerts = new ArrayList<>();

    // Signup endpoint
    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String password) {
        if (userStore.containsKey(username)) {
            return "User already exists!";
        }
        userStore.put(username, password);
        return "Signup successful!";
    }

    // Login endpoint
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if (userStore.containsKey(username) && userStore.get(username).equals(password)) {
            return "Login successful!";
        }
        return "Invalid credentials!";
    }

    // Cheating report endpoint
    @PostMapping("/cheating")
    public String reportCheating(@RequestParam String username, @RequestParam String alertType) {
        CheatingAlert alert = new CheatingAlert(username, alertType, LocalDateTime.now().toString());
        alerts.add(alert);
        return "Cheating alert logged!";
    }

    // View cheat alerts endpoint
    @GetMapping("/alerts")
    public List<CheatingAlert> getAlerts() {
        return alerts;
    }
}

