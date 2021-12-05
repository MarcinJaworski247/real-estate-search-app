package com.engine.realestatesearchapp.services;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Profile("!test")
@Service
@AllArgsConstructor
public class CurrentUserService {

    public String getUsername() {
        try {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

}
