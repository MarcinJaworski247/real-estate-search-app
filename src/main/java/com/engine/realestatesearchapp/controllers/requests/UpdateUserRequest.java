package com.engine.realestatesearchapp.controllers.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Request to update user")
public class UpdateUserRequest {

    @ApiModelProperty(notes = "Password")
    private String password;

    @ApiModelProperty(notes = "Phone number", example = "534129099")
    private String phoneNumber;

    public Optional<String> getPassword() {
        return Optional.ofNullable(password);
    }

    public Optional<String> getPhoneNumber() {
        return Optional.ofNullable(phoneNumber);
    }
}
