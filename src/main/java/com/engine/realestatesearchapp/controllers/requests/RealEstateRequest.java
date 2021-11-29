package com.engine.realestatesearchapp.controllers.requests;

import com.engine.realestatesearchapp.utilities.exceptions.InvalidRequestException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Request to create real estate")
public class RealEstateRequest {

    @NotBlank
    @ApiModelProperty(notes = "Offer title", example = "Cosy apartment for nice people", required = true)
    private String title;

    @NotBlank
    @ApiModelProperty(notes = "Offer description", example = "Here are some more details about the apartment.",
            required = true)
    private String description;

    @NotBlank
    @ApiModelProperty(notes = "Offer category", example = "Mieszkania", required = true)
    private String category;

    @NotBlank
    @ApiModelProperty(notes = "Offer type", example = "Wynajem", required = true)
    private String offerType;

    @NotNull
    @ApiModelProperty(notes = "Real estate price in zloty", example = "1100", required = true)
    private BigDecimal price;

    @NotNull
    @ApiModelProperty(notes = "Real estate size in square meters", example = "35", required = true)
    private BigDecimal size;

    @ApiModelProperty(notes = "Rent - required for flats", example = "400")
    private BigDecimal rent;

    @ApiModelProperty(notes = "Whether real estate furnished or not", example = "true")
    private boolean furnished;

    @ApiModelProperty(notes = "Floors number of real estate or on which floor real estate is", example = "2")
    private short floors;

    @ApiModelProperty(notes = "Rooms number - required for houses, flats and premises", example = "2")
    private short roomsNumber;

    @ApiModelProperty(notes = "Plot size in square meters - required for houses", example = "150")
    private BigDecimal plotSize;

    @NotNull
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

    public void validateHouseFields() {
        if (this.houseType == null || this.plotSize == null) {
            throw new InvalidRequestException("House type and plot size are mandatory.");
        }
    }

    public void validatePlotFields() {
        if (this.plotType == null) {
            throw new InvalidRequestException("Plot type is mandatory.");
        }
    }
}
