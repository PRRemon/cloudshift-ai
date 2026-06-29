//package com.parthraval.cloudshift.organization.repository;
//
//import com.parthraval.cloudshift.organization.entity.Organization;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
//import org.springframework.test.context.ActiveProfiles;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.time.Instant;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Testcontainers
//@DataJpaTest
//@ActiveProfiles("test")
//class OrganizationRepositoryIntegrationTest {
//
//    @Container
//    @ServiceConnection
//    static PostgreSQLContainer<?> postgres =
//            new PostgreSQLContainer<>("postgres:17");
//
//    @Autowired
//    private OrganizationRepository organizationRepository;
//
//    @Test
//    void shouldSaveOrganization() {
//
//        Organization organization = new Organization();
//
//        organization.setName("OpenAI");
//        organization.setDescription("Artificial Intelligence");
//        organization.setCreatedAt(Instant.now());
//        organization.setUpdatedAt(Instant.now());
//
//        Organization saved =
//                organizationRepository.save(organization);
//
//        assertThat(saved.getId()).isNotNull();
//        assertThat(saved.getName()).isEqualTo("OpenAI");
//
//    }
//}