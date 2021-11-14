package com.engine.realestatesearchapp.controllers.assemblers;

import com.engine.realestatesearchapp.controllers.RealEstateController;
import com.engine.realestatesearchapp.controllers.resources.RealEstateResource;
import com.engine.realestatesearchapp.repositiories.entities.RealEstate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RealEstateAssembler extends RepresentationModelAssemblerSupport<RealEstate, RealEstateResource> {

    @Autowired
    public RealEstateAssembler() {
        super(RealEstateController.class, RealEstateResource.class);
    }

    @Override
    public RealEstateResource toModel(RealEstate entity) {
        RealEstateResource resource = CommonAssembler.mapToResource(entity);
        resource.setFiles(entity.getFiles().stream().map(CommonAssembler::mapToResource).collect(Collectors.toList()));
        return resource;
    }

}
