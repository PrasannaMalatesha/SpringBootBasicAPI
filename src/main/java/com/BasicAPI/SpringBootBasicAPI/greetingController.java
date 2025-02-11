package com.BasicAPI.SpringBootBasicAPI;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController  // Marks this class as a REST Controller
@RequestMapping("/api/greetings")  // Base path for all endpoints in this class
public class greetingController {

    private Map<Long, String> greetings = new HashMap<>();
    private long idCounter = 1;


    @GetMapping("/hello")  // Handles GET requests to /api/greetings/hello
    public String sayHello() {
        return "Hello, Spring Boot!!";
    }

    @PostMapping("/add")  // Handles POST requests to /api/greetings/add
    public Map<String, Object> addGreeting(@RequestBody Map<String, String> name) {
        long id = idCounter++;
        String greetingMessage = "Hello, " + name.get("name") + "!";

        greetings.put(id, greetingMessage);

        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("greeting", greetingMessage);

        return response;
    }

    @GetMapping("/{id}")  // Read operation
    public String getGreeting(@PathVariable long id) {
        return greetings.getOrDefault(id, "Greeting not found");
    }

    @PutMapping("/{id}")  // Update operation
    public String updateGreeting(@PathVariable long id, @RequestBody String name) {
        if (greetings.containsKey(id)) {
            greetings.put(id, name);
            return "Greeting updated to: " + name;
        } else {
            return "Greeting not found";
        }
    }

    @DeleteMapping("/{id}")  // Delete operation
    public String deleteGreeting(@PathVariable long id) {
        if (greetings.containsKey(id)) {
            greetings.remove(id);
            return "Greeting deleted";
        } else {
            return "Greeting not found";
        }
    }

    @GetMapping
    public Map<Long, String> getAllGreetings() {
        return greetings;
    }

}