package com.engine.realestatesearchapp.controllers.assemblers;

import com.engine.realestatesearchapp.controllers.RealEstateController;
import com.engine.realestatesearchapp.controllers.resources.RealEstateResource;
import com.engine.realestatesearchapp.repositiories.entities.RealEstate;
import com.engine.realestatesearchapp.services.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RealEstateAssembler extends RepresentationModelAssemblerSupport<RealEstate, RealEstateResource> {

    private final CommonAssembler assembler;
    private final RealEstateService realEstateService;

    @Autowired
    public RealEstateAssembler(CommonAssembler assembler, RealEstateService realEstateService) {
        super(RealEstateController.class, RealEstateResource.class);
        this.assembler = assembler;
        this.realEstateService = realEstateService;
    }

    @Override
    public RealEstateResource toModel(RealEstate entity) {
        RealEstateResource resource = assembler.mapToResourceWithAvatar(entity);
        resource.setFavourite(realEstateService.getCurrentUserFavourites().contains(entity));
        return resource;
    }

}
