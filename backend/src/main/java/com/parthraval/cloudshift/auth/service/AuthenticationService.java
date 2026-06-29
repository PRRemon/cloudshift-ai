package com.parthraval.cloudshift.auth.service;

import com.parthraval.cloudshift.auth.dto.LoginRequest;
import com.parthraval.cloudshift.auth.dto.LoginResponse;
import com.parthraval.cloudshift.auth.dto.RegisterRequest;
import com.parthraval.cloudshift.auth.dto.RegisterResponse;
import com.parthraval.cloudshift.user.entity.User;
import com.parthraval.cloudshift.user.mapper.UserMapper;
import com.parthraval.cloudshift.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final org.springframework.security.core.userdetails.UserDetailsService userDetailsService;

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "Email already registered : " + request.getEmail());
        }

        String encodedPassword =
                passwordEncoder.encode(request.getPassword());

        User user =
                userMapper.toEntity(request, encodedPassword);

        User saved =
                userRepository.save(user);

        return userMapper.toResponse(saved);
    }

    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getEmail());

        String token =
                jwtService.generateToken(userDetails);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        return LoginResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(86400L)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

}
