package com.parthraval.cloudshift.assessment.mapper;

import com.parthraval.cloudshift.assessment.dto.AssessmentRequest;
import com.parthraval.cloudshift.assessment.dto.AssessmentResponse;
import com.parthraval.cloudshift.assessment.entity.Assessment;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class AssessmentMapper {

    public Assessment toEntity(AssessmentRequest request) {

        return Assessment.builder()
                .applicationName(request.getApplicationName())
                .applicationType(request.getApplicationType())
                .language(request.getLanguage())
                .framework(request.getFramework())
                .database(request.getDatabase())
                .serverCount(request.getServerCount())
                .monthlyUsers(request.getMonthlyUsers())
                .storageGb(request.getStorageGb())
                .trafficPattern(request.getTrafficPattern())
                .currentHosting(request.getCurrentHosting())
                .additionalRequirements(request.getAdditionalRequirements())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public AssessmentResponse toResponse(Assessment entity) {

        return AssessmentResponse.builder()
                .id(entity.getId())
                .applicationName(entity.getApplicationName())
                .applicationType(entity.getApplicationType())
                .language(entity.getLanguage())
                .framework(entity.getFramework())
                .database(entity.getDatabase())
                .serverCount(entity.getServerCount())
                .monthlyUsers(entity.getMonthlyUsers())
                .storageGb(entity.getStorageGb())
                .trafficPattern(entity.getTrafficPattern())
                .currentHosting(entity.getCurrentHosting())
                .additionalRequirements(entity.getAdditionalRequirements())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}