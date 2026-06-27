package com.parthraval.cloudshift.organization.controller;

import com.parthraval.cloudshift.common.dto.ApiResponse;
import com.parthraval.cloudshift.organization.dto.CreateOrganizationRequest;
import com.parthraval.cloudshift.organization.dto.OrganizationResponse;
import com.parthraval.cloudshift.organization.service.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrganizationResponse>> createOrganization(
            @Valid @RequestBody CreateOrganizationRequest request) {

        OrganizationResponse response =
                organizationService.createOrganization(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Organization created successfully",
                        response
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrganizationResponse>> getOrganizationById(
            @PathVariable UUID id) {

        OrganizationResponse response =
                organizationService.getOrganizationById(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Organization retrieved successfully",
                        response
                )
        );
    }
}