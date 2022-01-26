package com.engine.realestatesearchapp.services;

import com.engine.realestatesearchapp.repositiories.LocalizationRepository;
import com.engine.realestatesearchapp.repositiories.entities.Localization;
import com.engine.realestatesearchapp.utilities.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalizationService {

    private final LocalizationRepository localizationRepository;

    public List<Localization> getLocalizations() {
        return localizationRepository.findAll();
    }

    public Localization getLocalizationById(Integer id) {
        return localizationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Localization with id %s not found", id)));
    }

}
