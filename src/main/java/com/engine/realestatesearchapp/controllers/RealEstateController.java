package com.engine.realestatesearchapp.controllers;

import com.engine.realestatesearchapp.controllers.requests.RealEstateRequest;
import com.engine.realestatesearchapp.controllers.resources.RealEstateResource;
import com.engine.realestatesearchapp.repositiories.entities.RealEstate;
import com.engine.realestatesearchapp.services.RealEstateService;
import com.engine.realestatesearchapp.utilities.filters.RealEstateQueryFilters;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("real-estate")
@RequiredArgsConstructor
public class RealEstateController {

    private final RealEstateService realEstateService;
    private final RealEstateAssembler realEstateAssembler;

    @GetMapping
    @ApiOperation(value = "Get real estates")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you " +
                    "want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records " +
                    "per page", defaultValue = "10")
    })
    public PagedModel<RealEstateResource> getMerchants(RealEstateQueryFilters filters,
            PagedResourcesAssembler<RealEstate> paged, @ApiIgnore @PageableDefault Pageable pageable) {
        return paged.toModel(realEstateService.getRealEstatePage(filters, pageable), realEstateAssembler);
    }

    @GetMapping("/{real_estate_id}")
    public RealEstateResource getRealEstateById(@PathVariable("real_estate_id") UUID realEstateId) {
        return CommonAssembler.mapToResource(realEstateService.getRealEstateById(realEstateId));
    }

    @PostMapping
    public RealEstateResource createRealEstate(@RequestBody @Valid RealEstateRequest request) {
        return CommonAssembler.mapToResource(realEstateService.createRealEstate(request));
    }
}
