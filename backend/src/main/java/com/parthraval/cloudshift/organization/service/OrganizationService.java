package com.parthraval.cloudshift.organization.service;

import com.parthraval.cloudshift.organization.dto.CreateOrganizationRequest;
import com.parthraval.cloudshift.organization.dto.OrganizationResponse;

public interface OrganizationService {

    OrganizationResponse createOrganization(
            CreateOrganizationRequest request
    );

}