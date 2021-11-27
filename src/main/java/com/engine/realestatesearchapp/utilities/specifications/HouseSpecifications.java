package com.engine.realestatesearchapp.utilities.specifications;

import com.engine.realestatesearchapp.repositiories.entities.House;
import com.engine.realestatesearchapp.repositiories.entities.RealEstate_;
import com.engine.realestatesearchapp.repositiories.enums.OfferType;
import com.engine.realestatesearchapp.repositiories.enums.RealEstateCategory;
import com.engine.realestatesearchapp.utilities.filters.RealEstateQueryFilters;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HouseSpecifications {

    public static Specification<House> getHouseSpecifications(RealEstateQueryFilters filters) {
        Specification<House> specification = Specification.where(null);
/*
        if (filters.getOfferType() != null) {
            specification = specification.and(offerTypeEquals(filters.getOfferType()));
        }*/
        if (filters.getCreatedAtFrom() != null) {
            specification = specification.and(createdAtGreaterThanOrEqualTo(filters.getCreatedAtFrom()));
        }
        if (filters.getCreatedAtTo() != null) {
            specification = specification.and(createdAtLessThanOrEqualTo(filters.getCreatedAtTo()));
        }
        return specification;
    }

    public static Specification<House> offerTypeEquals(OfferType offerType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(House_.ID), offerType);
    }

    public static Specification<House> createdAtGreaterThanOrEqualTo(LocalDateTime createdAtFrom) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get(RealEstate_.CREATED_AT), createdAtFrom);
    }

    public static Specification<House> createdAtLessThanOrEqualTo(LocalDateTime createdAtTo) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get(House_.CREATED_AT), createdAtTo);
    }

}
