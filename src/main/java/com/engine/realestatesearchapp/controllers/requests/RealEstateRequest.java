package com.engine.realestatesearchapp.controllers.requests;

import com.engine.realestatesearchapp.repositiories.enums.FlatType;
import com.engine.realestatesearchapp.repositiories.enums.HouseType;
import com.engine.realestatesearchapp.repositiories.enums.OfferType;
import com.engine.realestatesearchapp.repositiories.enums.PlotType;
import com.engine.realestatesearchapp.repositiories.enums.PremisesPurpose;
import com.engine.realestatesearchapp.repositiories.enums.RealEstateCategory;
import com.engine.realestatesearchapp.repositiories.enums.RoomType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Request to create real estate")
public class RealEstateRequest {
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
    private short floorsNumber;
    private OfferType offerType;
    private UUID localizationId;
    private PlotType plotType;
    private RoomType roomType;
    private HouseType houseType;
    private FlatType flatType;
    private PremisesPurpose premisesPurpose;
}
