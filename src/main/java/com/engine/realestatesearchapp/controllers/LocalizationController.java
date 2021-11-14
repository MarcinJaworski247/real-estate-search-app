package com.engine.realestatesearchapp.controllers;

import com.engine.realestatesearchapp.repositiories.entities.Localization;
import com.engine.realestatesearchapp.services.LocalizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/localizations")
@RequiredArgsConstructor
public class LocalizationController {

    private final LocalizationService localizationService;

    @GetMapping
    public List<Localization> getLocalizations() {
        return localizationService.getLocalizations();
    }
}
