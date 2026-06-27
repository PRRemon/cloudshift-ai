package com.parthraval.cloudshift.organization.service;

import com.parthraval.cloudshift.organization.dto.CreateOrganizationRequest;
import com.parthraval.cloudshift.organization.dto.OrganizationResponse;

import java.util.UUID;

public interface OrganizationService {

    OrganizationResponse createOrganization(
            CreateOrganizationRequest request
    );

    OrganizationResponse getOrganizationById(UUID id);

}