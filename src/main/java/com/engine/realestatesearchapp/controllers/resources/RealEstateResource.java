package com.engine.realestatesearchapp.controllers.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "RealEstateResource", description = "Real estate details")
public class RealEstateResource extends RepresentationModel<RealEstateResource> {
    private UUID id;
    private String title;
    private String description;
    private String category;
    private BigDecimal price;
    private BigDecimal size;
    private BigDecimal rent;
    private boolean furnished;
    private short level;
    private short roomsNumber;
    private BigDecimal plotSize;
    private short floors;
    private String offerType;
    private UUID localizationId;
    private String plotType;
    private String roomType;
    private String houseType;
    private String flatType;
    private String premisesPurpose;
    private boolean sold = false;
}
