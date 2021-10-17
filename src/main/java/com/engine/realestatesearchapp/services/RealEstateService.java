package com.engine.realestatesearchapp.services;

import com.engine.realestatesearchapp.controllers.CommonAssembler;
import com.engine.realestatesearchapp.controllers.requests.RealEstateRequest;
import com.engine.realestatesearchapp.controllers.requests.UpdateRealEstateRequest;
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

    public RealEstate updateRealEstate(UUID realEstateId, UpdateRealEstateRequest request) {
        RealEstate entity = getRealEstateById(realEstateId);
        request.getTitle().ifPresent(entity::setTitle);
        request.getDescription().ifPresent(entity::setDescription);
        request.getOfferType().ifPresent(entity::setOfferType);
        request.getPrice().ifPresent(entity::setPrice);
        request.getSize().ifPresent(entity::setSize);
        request.getRent().ifPresent(entity::setRent);
        request.isFurnished().ifPresent(entity::setFurnished);
        request.getFloors().ifPresent(entity::setFloors);
        request.getRoomsNumber().ifPresent(entity::setRoomsNumber);
        request.getPlotSize().ifPresent(entity::setPlotSize);
        //request.getLocalizationId().ifPresent();
        request.getPlotType().ifPresent(entity::setPlotType);
        request.getRoomType().ifPresent(entity::setRoomType);
        request.getHouseType().ifPresent(entity::setHouseType);
        request.getFlatType().ifPresent(entity::setFlatType);
        request.getPremisesPurpose().ifPresent(entity::setPremisesPurpose);
        return realEstateRepository.save(entity);
    }

    public RealEstate setRealEstateAsSold(UUID realEstateId) {
        RealEstate entity = getRealEstateById(realEstateId);
        entity.setSold(true);
        return realEstateRepository.save(entity);
    }

    public RealEstate deleteRealEstate(UUID realEstateId) {
        RealEstate entity = getRealEstateById(realEstateId);
        entity.setDeleted(true);
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
