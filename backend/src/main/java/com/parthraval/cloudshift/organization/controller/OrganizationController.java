package com.parthraval.cloudshift.organization.controller;

import com.parthraval.cloudshift.common.dto.ApiResponse;
import com.parthraval.cloudshift.organization.dto.CreateOrganizationRequest;
import com.parthraval.cloudshift.organization.dto.OrganizationResponse;
import com.parthraval.cloudshift.organization.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Organization",
        description = "Organization Management APIs"
)
@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @Operation(
            summary = "Create Organization",
            description = "Creates a new organization."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Organization created successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid request payload"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "409",
                    description = "Organization already exists"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
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

    @Operation(
            summary = "Get Organization by ID"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Organization found"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Organization not found"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
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

    @Operation(
            summary = "Get All Organizations"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Organizations retrieved successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @GetMapping
    public ResponseEntity<ApiResponse<List<OrganizationResponse>>> getAllOrganizations() {

        List<OrganizationResponse> organizations =
                organizationService.getAllOrganizations();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Organizations retrieved successfully",
                        organizations
                )
        );
    }

    @Operation(
            summary = "Delete Organization by ID"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "204",
                    description = "Organization deleted successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Organization not found"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(
            @PathVariable UUID id) {

        organizationService.deleteOrganization(id);

        return ResponseEntity.noContent().build();
    }
}