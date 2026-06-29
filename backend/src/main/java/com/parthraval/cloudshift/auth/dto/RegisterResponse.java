package com.parthraval.cloudshift.auth.dto;

import com.parthraval.cloudshift.user.entity.Role;
import lombok.Builder;

import java.util.UUID;

@Builder
public record RegisterResponse(

        UUID id,

        String firstName,

        String lastName,

        String email,

        Role role

) {
}