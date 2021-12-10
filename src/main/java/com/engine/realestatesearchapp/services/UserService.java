package com.engine.realestatesearchapp.services;

import com.engine.realestatesearchapp.controllers.requests.UpdateUserRequest;
import com.engine.realestatesearchapp.controllers.requests.UserRequest;
import com.engine.realestatesearchapp.repositiories.UserRepository;
import com.engine.realestatesearchapp.repositiories.entities.User;
import com.engine.realestatesearchapp.repositiories.enums.UserRole;
import com.engine.realestatesearchapp.utilities.exceptions.AlreadyExistsException;
import com.engine.realestatesearchapp.utilities.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;
    private final BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void delete(UUID id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public User getCurrentUser() {
        return findByUsername(currentUserService.getUsername());
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(format("User with username %s not found", username)));
    }

    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("User with id %s not found", id)));
    }

    public User updateUser(UpdateUserRequest request) {
        User entity = getCurrentUser();
        request.getPassword().ifPresent(password -> entity.setPassword(bcryptEncoder.encode(password)));
        request.getPhoneNumber().ifPresent(entity::setPhoneNumber);
        return userRepository.save(entity);
    }

    public User saveUser(User user) {
       return userRepository.save(user);
    }

    public User save(UserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AlreadyExistsException(format("User with username %s already exists", request.getUsername()));
        }
        User entity = User.builder().username(request.getUsername())
                .password(bcryptEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .role(UserRole.USER)
                .build();
        return userRepository.save(entity);
    }

}