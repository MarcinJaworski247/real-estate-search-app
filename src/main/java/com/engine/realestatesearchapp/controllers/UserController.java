package com.engine.realestatesearchapp.controllers;

import com.engine.realestatesearchapp.controllers.assemblers.CommonAssembler;
import com.engine.realestatesearchapp.controllers.requests.UpdateUserRequest;
import com.engine.realestatesearchapp.controllers.requests.UserRequest;
import com.engine.realestatesearchapp.controllers.resources.UserResource;
import com.engine.realestatesearchapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CommonAssembler assembler;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/users")
    public List<UserResource> listUser() {
        return userService.findAll().stream()
                .map(assembler::mapToUserResource)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/users/{id}")
    public UserResource getUserById(@PathVariable(value = "id") UUID id) {
        return assembler.mapToUserResource(userService.findById(id));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(value = "/users/current")
    public UserResource getCurrentUser() {
        return assembler.mapToUserResource(userService.getCurrentUser());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PatchMapping(value = "/users/current")
    public UserResource updateCurrentUser(@RequestBody @Valid UpdateUserRequest request) {
        return assembler.mapToUserResource(userService.updateUser(request));
    }

    @PostMapping(value = "/register")
    public UserResource saveUser(@RequestBody UserRequest user) {
        return assembler.mapToUserResource(userService.save(user));
    }
}
