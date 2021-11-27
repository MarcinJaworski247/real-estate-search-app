package com.engine.realestatesearchapp.controllers.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Request to update real estate")
public class UpdateRealEstateRequest {

    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public Optional<String> getOfferType() {
        return Optional.ofNullable(offerType);
    }

    public Optional<BigDecimal> getPrice() {
        return Optional.ofNullable(price);
    }

    public Optional<BigDecimal> getSize() {
        return Optional.ofNullable(size);
    }

    public Optional<BigDecimal> getRent() {
        return Optional.ofNullable(rent);
    }

    public Optional<Boolean> isFurnished() {
        return Optional.ofNullable(furnished);
    }

    public Optional<Short> getFloors() {
        return Optional.ofNullable(floors);
    }

    public Optional<Short> getRoomsNumber() {
        return Optional.ofNullable(roomsNumber);
    }

    public Optional<BigDecimal> getPlotSize() {
        return Optional.ofNullable(plotSize);
    }

    public Optional<Integer> getLocalizationId() {
        return Optional.ofNullable(localizationId);
    }

    public Optional<String> getPlotType() {
        return Optional.ofNullable(plotType);
    }

    public Optional<String> getRoomType() {
        return Optional.ofNullable(roomType);
    }

    public Optional<String> getHouseType() {
        return Optional.ofNullable(houseType);
    }

    public Optional<String> getFlatType() {
        return Optional.ofNullable(flatType);
    }

    public Optional<String> getPremisesPurpose() {
        return Optional.ofNullable(premisesPurpose);
    }

    @ApiModelProperty(notes = "Offer title", example = "Cosy apartment for nice people")
    private String title;

    @ApiModelProperty(notes = "Offer description", example = "Here are some more details about the apartment.")
    private String description;

    @ApiModelProperty(notes = "Offer type", example = "Wynajem")
    private String offerType;

    @ApiModelProperty(notes = "Real estate price in zloty", example = "1100")
    private BigDecimal price;

    @ApiModelProperty(notes = "Real estate size in square meters", example = "35")
    private BigDecimal size;

    @ApiModelProperty(notes = "Rent - required for flats", example = "400")
    private BigDecimal rent;

    @ApiModelProperty(notes = "Whether real estate furnished or not", example = "true")
    private Boolean furnished;

    @ApiModelProperty(notes = "Floors number of real estate or on which floor real estate is", example = "2")
    private Short floors;

    @ApiModelProperty(notes = "Rooms number - required for houses, flats and premises", example = "2")
    private Short roomsNumber;

    @ApiModelProperty(notes = "Plot size in square meters - required for houses", example = "150")
    private BigDecimal plotSize;

    @ApiModelProperty(notes = "Real estate localization id", example = "1",
            required = true)
    private Integer localizationId;

    @ApiModelProperty(notes = "Plot type - required for category 'Działki'", example = "Rolne")
    private String plotType;

    @ApiModelProperty(notes = "Room type - required for category 'Stancje i pokoje'", example = "Jednoosobowe")
    private String roomType;

    @ApiModelProperty(notes = "House type - required for category 'Domy'", example = "Wolnostojące")
    private String houseType;

    @ApiModelProperty(notes = "Flat type - required for category 'Mieszkania'", example = "Bloki")
    private String flatType;

    @ApiModelProperty(notes = "Premises purpose - required for category 'Biura i lokale'", example = "Usługowe")
    private String premisesPurpose;
}
