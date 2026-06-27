package com.parthraval.cloudshift.organization.service;

import com.parthraval.cloudshift.common.exception.BusinessException;
import com.parthraval.cloudshift.common.exception.ResourceNotFoundException;
import com.parthraval.cloudshift.organization.dto.CreateOrganizationRequest;
import com.parthraval.cloudshift.organization.dto.OrganizationResponse;
import com.parthraval.cloudshift.organization.entity.Organization;
import com.parthraval.cloudshift.organization.repository.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
//@RequiredArgsConstructor
public class OrganizationServiceImpl
        implements OrganizationService {

    private static final Logger log =
            LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private final OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public OrganizationResponse createOrganization(
            CreateOrganizationRequest request
    ) {
        log.info("Creating organization with name={}", request.name());
        if (organizationRepository.existsByNameIgnoreCase(request.name())) {
            log.warn(
                    "Organization already exists. name={}",
                    request.name()
            );
            throw new BusinessException("Organization already exists.");
        }

        Organization organization = new Organization();

        organization.setName(request.name());
        organization.setDescription(request.description());

        Organization saved =
                organizationRepository.save(organization);
        log.info(
                "Organization created successfully. id={}, name={}",
                saved.getId(),
                saved.getName()
        );
        return new OrganizationResponse(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getCreatedAt(),
                saved.getUpdatedAt()
        );

    }

    @Override
    public OrganizationResponse getOrganizationById(UUID id) {
        log.info("Fetching organization. id={}", id);

        Organization organization = organizationRepository
                .findById(id)
                .orElseThrow(() -> {

                    log.warn("Organization not found. id={}", id);

                    return new ResourceNotFoundException(
                            "Organization not found with id: " + id
                    );
                });

        return new OrganizationResponse(
                organization.getId(),
                organization.getName(),
                organization.getDescription(),
                organization.getCreatedAt(),
                organization.getUpdatedAt()
        );
    }

    @Override
    public List<OrganizationResponse> getAllOrganizations() {

        return organizationRepository.findAll()
                .stream()
                .map(organization -> new OrganizationResponse(
                        organization.getId(),
                        organization.getName(),
                        organization.getDescription(),
                        organization.getCreatedAt(),
                        organization.getUpdatedAt()
                ))
                .toList();
    }

    @Override
    public void deleteOrganization(UUID id) {
        log.info(
                "Deleting organization id={}",
                id
        );
        Organization organization = organizationRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Organization not found with id: " + id
                        ));

        log.info(
                "Organization deleted successfully. id={}",
                id
        );
        organizationRepository.delete(organization);
    }
}