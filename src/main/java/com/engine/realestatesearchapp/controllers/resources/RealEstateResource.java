package com.engine.realestatesearchapp.controllers.resources;

import com.engine.realestatesearchapp.repositiories.enums.RealEstateCategory;
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
}
