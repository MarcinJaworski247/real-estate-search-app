package com.engine.realestatesearchapp.controllers.requests;

import com.engine.realestatesearchapp.repositiories.enums.RealEstateCategory;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Request to create real estate")
public class RealEstateRequest {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private RealEstateCategory category;

    @Size
    @NotNull
    private BigDecimal price;

    @Size
    @NotNull
    private BigDecimal size;

}
