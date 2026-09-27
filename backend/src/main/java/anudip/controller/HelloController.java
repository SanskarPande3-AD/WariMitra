package anudip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@Controller
@ResponseBody
@RequestMapping("/api")
public class HelloController {

    // Home
    @GetMapping("/")
    public String home() {
        return """
                🌾 Welcome to VariMitra
                
                Available APIs:
                --------------------------
                GET  /api/
                GET  /api/health
                GET  /api/about
                GET  /api/time
                GET  /api/weather
                GET  /api/crops
                GET  /api/tips
                GET  /api/contact
                """;
    }

    // Health Check
    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();

        response.put("status", "Running");
        response.put("application", "VariMitra");
        response.put("version", "1.0");
        response.put("time", LocalDateTime.now());

        return response;
    }

    // About
    @GetMapping("/about")
    public String about() {
        return """
                VariMitra
                
                Smart Farming Assistance Platform
                
                Features:
                ✔ Weather Updates
                ✔ Crop Suggestions
                ✔ Fertilizer Guidance
                ✔ Market Price Tracking
                ✔ Disease Detection
                """;
    }

    // Current Time
    @GetMapping("/time")
    public String time() {
        return "Current Server Time : " + LocalDateTime.now();
    }

    // Sample Weather
    @GetMapping("/weather")
    public Map<String, String> weather() {

        Map<String, String> weather = new HashMap<>();

        weather.put("Temperature", "29°C");
        weather.put("Humidity", "74%");
        weather.put("Condition", "Cloudy");
        weather.put("Rain Chance", "60%");

        return weather;
    }

    // Crop List
    @GetMapping("/crops")
    public List<String> crops() {

        return Arrays.asList(
                "Rice",
                "Wheat",
                "Sugarcane",
                "Cotton",
                "Soybean",
                "Maize"
        );
    }

    // Farming Tip
    @GetMapping("/tips")
    public String tip() {

        String[] tips = {
                "Water crops early in the morning.",
                "Test soil before using fertilizers.",
                "Use drip irrigation to save water.",
                "Rotate crops every season.",
                "Monitor pests regularly."
        };

        Random random = new Random();

        return tips[random.nextInt(tips.length)];
    }

    // Contact
    @GetMapping("/contact")
    public Map<String, String> contact() {

        Map<String, String> contact = new HashMap<>();

        contact.put("Email", "support@varimitra.com");
        contact.put("Phone", "+91-9876543210");
        contact.put("Location", "Pune, Maharashtra");

        return contact;
    }
}