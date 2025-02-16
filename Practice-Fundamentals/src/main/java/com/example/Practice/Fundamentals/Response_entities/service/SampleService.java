package com.example.Practice.Fundamentals.Response_entities.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SampleService {
    public ResponseEntity<?> SampleService() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hello World");
    }
}
