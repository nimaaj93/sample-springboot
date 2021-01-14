package io.recruitment.assessment.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/hello-world")
public class HelloController {

    @GetMapping
    Map<String, String> helloWorld() {
        return Map.of("message", "Hi there!");
    }

}
