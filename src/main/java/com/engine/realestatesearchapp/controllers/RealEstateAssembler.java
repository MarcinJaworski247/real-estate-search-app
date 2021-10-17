package com.engine.realestatesearchapp.controllers;

import com.engine.realestatesearchapp.controllers.resources.RealEstateResource;
import com.engine.realestatesearchapp.repositiories.entities.RealEstate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RealEstateAssembler extends RepresentationModelAssemblerSupport<RealEstate, RealEstateResource> {

    @Autowired
    public RealEstateAssembler() {
        super(RealEstateController.class, RealEstateResource.class);
    }

    @Override
    public RealEstateResource toModel(RealEstate entity) {
        return CommonAssembler.mapToResource(entity);
    }

}
