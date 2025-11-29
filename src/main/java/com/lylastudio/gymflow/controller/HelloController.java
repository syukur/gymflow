package com.lylastudio.gymflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Operation(description = "Hello Word for testing")
    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World";
    }
}
