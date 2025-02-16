package com.example.Practice.Fundamentals.Response_entities.controller;

import com.example.Practice.Fundamentals.Response_entities.service.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class SampleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);
    @Autowired
    private SampleService sampleService;
    @GetMapping("/example")
    public ResponseEntity<?> getSample(){
        LOGGER.info("GetSample method is invoked");
        return sampleService.SampleService();
    }
}
