package com.parthraval.cloudshift.organization.service;

import com.parthraval.cloudshift.common.exception.BusinessException;
import com.parthraval.cloudshift.common.exception.ResourceNotFoundException;
import com.parthraval.cloudshift.organization.dto.CreateOrganizationRequest;
import com.parthraval.cloudshift.organization.dto.OrganizationResponse;
import com.parthraval.cloudshift.organization.entity.Organization;
import com.parthraval.cloudshift.organization.repository.OrganizationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceImplTest {

    @Mock
    private OrganizationRepository organizationRepository;

    @InjectMocks
    private OrganizationServiceImpl organizationService;

    @Test
    void shouldCreateOrganizationSuccessfully() {

        CreateOrganizationRequest request =
                new CreateOrganizationRequest(
                        "OpenAI",
                        "Artificial Intelligence"
                );

        Organization savedOrganization = new Organization();

        UUID id = UUID.randomUUID();

        savedOrganization.setId(id);
        savedOrganization.setName("OpenAI");
        savedOrganization.setDescription("Artificial Intelligence");
        savedOrganization.setCreatedAt(Instant.now());
        savedOrganization.setUpdatedAt(Instant.now());

        when(organizationRepository.existsByNameIgnoreCase("OpenAI"))
                .thenReturn(false);

        when(organizationRepository.save(any(Organization.class)))
                .thenReturn(savedOrganization);

        OrganizationResponse response =
                organizationService.createOrganization(request);

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(id);
        assertThat(response.name()).isEqualTo("OpenAI");

        verify(organizationRepository).save(any(Organization.class));
    }

    @Test
    void shouldThrowBusinessExceptionWhenOrganizationAlreadyExists() {

        CreateOrganizationRequest request =
                new CreateOrganizationRequest(
                        "OpenAI",
                        "Artificial Intelligence"
                );

        when(organizationRepository.existsByNameIgnoreCase("OpenAI"))
                .thenReturn(true);

        assertThatThrownBy(() ->
                organizationService.createOrganization(request))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Organization already exists.");

        verify(organizationRepository, never())
                .save(any());
    }

    @Test
    void shouldReturnOrganizationById() {

        UUID id = UUID.randomUUID();

        Organization organization = new Organization();

        organization.setId(id);
        organization.setName("Google");
        organization.setDescription("Search");
        organization.setCreatedAt(Instant.now());
        organization.setUpdatedAt(Instant.now());

        when(organizationRepository.findById(id))
                .thenReturn(Optional.of(organization));

        OrganizationResponse response =
                organizationService.getOrganizationById(id);

        assertThat(response.id()).isEqualTo(id);
        assertThat(response.name()).isEqualTo("Google");
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenOrganizationNotFound() {

        UUID id = UUID.randomUUID();

        when(organizationRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                organizationService.getOrganizationById(id))
                .isInstanceOf(ResourceNotFoundException.class);

        verify(organizationRepository)
                .findById(id);
    }

    @Test
    void shouldReturnAllOrganizations() {

        Organization org1 = new Organization();
        org1.setId(UUID.randomUUID());
        org1.setName("Google");

        Organization org2 = new Organization();
        org2.setId(UUID.randomUUID());
        org2.setName("OpenAI");

        when(organizationRepository.findAll())
                .thenReturn(List.of(org1, org2));

        List<OrganizationResponse> response =
                organizationService.getAllOrganizations();

        assertThat(response)
                .hasSize(2);

        verify(organizationRepository)
                .findAll();
    }

    @Test
    void shouldDeleteOrganizationSuccessfully() {

        UUID id = UUID.randomUUID();

        Organization organization = new Organization();

        organization.setId(id);

        when(organizationRepository.findById(id))
                .thenReturn(Optional.of(organization));

        organizationService.deleteOrganization(id);

        verify(organizationRepository)
                .delete(organization);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenDeletingUnknownOrganization() {

        UUID id = UUID.randomUUID();

        when(organizationRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                organizationService.deleteOrganization(id))
                .isInstanceOf(ResourceNotFoundException.class);

        verify(organizationRepository, never())
                .delete(any());
    }
}