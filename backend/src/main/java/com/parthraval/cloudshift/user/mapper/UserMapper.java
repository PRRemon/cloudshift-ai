package com.parthraval.cloudshift.user.mapper;

import com.parthraval.cloudshift.auth.dto.RegisterRequest;
import com.parthraval.cloudshift.auth.dto.RegisterResponse;
import com.parthraval.cloudshift.user.entity.Role;
import com.parthraval.cloudshift.user.entity.User;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserMapper {

    public User toEntity(RegisterRequest request, String encodedPassword) {

        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(encodedPassword)
                .role(Role.USER)
                .enabled(true)
                .createdAt(Instant.now())
                .build();

    }

    public RegisterResponse toResponse(User user) {

        return RegisterResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();

    }

}
