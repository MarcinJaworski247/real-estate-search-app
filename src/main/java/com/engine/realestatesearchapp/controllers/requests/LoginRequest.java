package com.engine.realestatesearchapp.controllers.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Request to create real estate")
public class LoginRequest {

    @NotBlank
    @ApiModelProperty(notes = "Username", required = true)
    private String username;

    @NotBlank
    @ApiModelProperty(notes = "Password", required = true)
    private String password;

}
