package com.engine.realestatesearchapp.services;

import java.util.Collection;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
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

    public boolean isAdmin() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                    .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

}
