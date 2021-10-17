package com.engine.realestatesearchapp.services;

import com.engine.realestatesearchapp.controllers.CommonAssembler;
import com.engine.realestatesearchapp.controllers.requests.RealEstateRequest;
import com.engine.realestatesearchapp.repositiories.RealEstateRepository;
import com.engine.realestatesearchapp.repositiories.entities.RealEstate;
import com.engine.realestatesearchapp.utilities.exceptions.NotFoundException;
import com.engine.realestatesearchapp.utilities.filters.RealEstateQueryFilters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.engine.realestatesearchapp.utilities.specifications.RealEstateSpecifications.getRealEstateSpecification;

@Service
@RequiredArgsConstructor
public class RealEstateService {

    private final RealEstateRepository realEstateRepository;

    public RealEstate createRealEstate(RealEstateRequest request) {
        RealEstate entity = CommonAssembler.mapToEntity(request);
        return realEstateRepository.save(entity);
    }

    public RealEstate getRealEstateById(UUID id) {
        return realEstateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Real estate with id %s not found", id)));
    }

    public Page<RealEstate> getRealEstatePage(RealEstateQueryFilters filters, Pageable pageable) {
        Specification<RealEstate> specification = getRealEstateSpecification(filters);
        return realEstateRepository.findAll(specification, pageable);
    }

}
