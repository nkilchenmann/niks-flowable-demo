package com.example.flowabledemo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VerifyController {
    @GetMapping("/verify")
    public ResponseEntity verify() {
        System.out.println("Verify has been called");
        return new ResponseEntity("Application up and running", HttpStatus.OK);
    }
}
