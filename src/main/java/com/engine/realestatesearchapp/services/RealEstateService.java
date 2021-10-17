package com.engine.realestatesearchapp.services;

import com.engine.realestatesearchapp.controllers.CommonAssembler;
import com.engine.realestatesearchapp.controllers.requests.RealEstateRequest;
import com.engine.realestatesearchapp.repositiories.RealEstateRepository;
import com.engine.realestatesearchapp.repositiories.entities.RealEstate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RealEstateService {

    private final RealEstateRepository realEstateRepository;

    public RealEstate createRealEstate(RealEstateRequest request) {
        RealEstate entity = CommonAssembler.mapToEntity(request);
        return realEstateRepository.save(entity);
    }
}
