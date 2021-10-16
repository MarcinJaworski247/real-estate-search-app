package com.engine.realestatesearchapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("real-estate")
@RequiredArgsConstructor
public class RealEstateController {

    @GetMapping("test")
    public String test() {
        return "Hello world!";
    }
}
