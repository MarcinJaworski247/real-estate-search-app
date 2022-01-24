package com.engine.realestatesearchapp.controllers.requests;

import io.swagger.annotations.*;
import java.math.*;
import java.util.*;
import javax.validation.constraints.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Request to ban or unbanoffer")
public class BanRealEstateRequest {

    @NotNull
    @ApiModelProperty(notes = "Whether offer should be banned", example = "true")
    private boolean banned;

    @ApiModelProperty(notes = "Comment for action", example = "Banned due inappropriate words.")
    private String comment;

}
