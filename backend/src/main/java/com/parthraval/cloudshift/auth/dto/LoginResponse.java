package com.parthraval.cloudshift.auth.dto;

import com.parthraval.cloudshift.user.entity.Role;
import lombok.Builder;

@Builder
public record LoginResponse(

        String accessToken,

        String tokenType,

        Long expiresIn,

        String email,

        Role role

) {
}