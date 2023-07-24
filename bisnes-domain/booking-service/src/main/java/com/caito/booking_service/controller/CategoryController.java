package com.caito.booking_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@RefreshScope
public class CategoryController {
    @Value("${test.prop}")
    private String testProp;

    @GetMapping
    public String getTestProp(){
        return testProp;
    }
}
