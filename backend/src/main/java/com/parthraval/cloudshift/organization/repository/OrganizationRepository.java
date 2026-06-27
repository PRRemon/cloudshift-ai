package com.parthraval.cloudshift.organization.repository;

import com.parthraval.cloudshift.organization.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrganizationRepository
        extends JpaRepository<Organization, UUID> {
}