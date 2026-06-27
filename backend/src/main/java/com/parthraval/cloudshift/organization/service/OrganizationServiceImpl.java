package com.parthraval.cloudshift.organization.service;

import com.parthraval.cloudshift.organization.dto.CreateOrganizationRequest;
import com.parthraval.cloudshift.organization.dto.OrganizationResponse;
import com.parthraval.cloudshift.organization.entity.Organization;
import com.parthraval.cloudshift.organization.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class OrganizationServiceImpl
        implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public OrganizationResponse createOrganization(
            CreateOrganizationRequest request
    ) {

        Organization organization = new Organization();

        organization.setName(request.name());
        organization.setDescription(request.description());

        Organization saved =
                organizationRepository.save(organization);

        return new OrganizationResponse(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getCreatedAt(),
                saved.getUpdatedAt()
        );

    }

}