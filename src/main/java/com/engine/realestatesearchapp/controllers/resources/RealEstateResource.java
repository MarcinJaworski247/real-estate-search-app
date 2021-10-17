package com.engine.realestatesearchapp.controllers.resources;

import com.engine.realestatesearchapp.repositiories.enums.FlatType;
import com.engine.realestatesearchapp.repositiories.enums.HouseType;
import com.engine.realestatesearchapp.repositiories.enums.OfferType;
import com.engine.realestatesearchapp.repositiories.enums.PlotType;
import com.engine.realestatesearchapp.repositiories.enums.PremisesPurpose;
import com.engine.realestatesearchapp.repositiories.enums.RealEstateCategory;
import com.engine.realestatesearchapp.repositiories.enums.RoomType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "RealEstateResource", description = "Real estate details")
public class RealEstateResource {
    private UUID id;
    private String title;
    private String description;
    private RealEstateCategory category;
    private BigDecimal price;
    private BigDecimal size;
    private BigDecimal rent;
    private boolean furnished;
    private short level;
    private short roomsNumber;
    private BigDecimal plotSize;
    private short floors;
    private OfferType offerType;
    private UUID localizationId;
    private PlotType plotType;
    private RoomType roomType;
    private HouseType houseType;
    private FlatType flatType;
    private PremisesPurpose premisesPurpose;
    private boolean sold = false;
}
