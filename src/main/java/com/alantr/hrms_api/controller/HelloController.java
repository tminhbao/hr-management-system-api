package com.alantr.hrms_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String getHelloWorld() {
        return "Hello World";
    }
}
