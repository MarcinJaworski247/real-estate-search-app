package com.engine.realestatesearchapp.controllers;

import com.engine.realestatesearchapp.controllers.requests.RealEstateRequest;
import com.engine.realestatesearchapp.controllers.resources.RealEstateResource;
import com.engine.realestatesearchapp.services.RealEstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("real-estate")
@RequiredArgsConstructor
public class RealEstateController {

    private final RealEstateService realEstateService;

    @GetMapping("test")
    public String test() {
        return "Hello world!";
    }

    @PostMapping
    public RealEstateResource createRealEstate(@RequestBody @Valid RealEstateRequest request) {
        return CommonAssembler.mapToResource(realEstateService.createRealEstate(request));
    }
}
