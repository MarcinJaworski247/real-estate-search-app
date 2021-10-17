package com.engine.realestatesearchapp.utilities.filters;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RealEstateQueryFilters {

/*    @ApiModelProperty(
            notes = "Real estate category",
            example = "Domy")
    private RealEstateCategory category;

    @ApiModelProperty(
            notes = "Offer type",
            example = "Wynajem")
    private OfferType offerType;*/

    @ApiModelProperty(
            notes = "Timestamp to filter out created date-time earlier than it / ISO8601",
            example = "2020-01-05T16:17:13.326")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAtFrom;

    @ApiModelProperty(
            notes = "Timestamp to filter out created date-time later than it / ISO8601",
            example = "2020-01-05T16:17:13.326")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAtTo;

}
