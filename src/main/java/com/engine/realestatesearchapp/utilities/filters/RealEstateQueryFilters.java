package com.engine.realestatesearchapp.utilities.filters;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RealEstateQueryFilters {

    @ApiModelProperty(
            notes = "Real estate category",
            example = "Domy")
    private String category;

    @ApiModelProperty(
            notes = "Real estate localization",
            example = "Gdańśk")
    private Long localizationId;

    @ApiModelProperty(
            notes = "Offer type",
            example = "Wynajem")
    private String offerType;

    @ApiModelProperty(
            notes = "Offer price from",
            example = "1000")
    private BigDecimal priceFrom;

    @ApiModelProperty(
            notes = "Offer price to",
            example = "1000")
    private BigDecimal priceTo;

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
