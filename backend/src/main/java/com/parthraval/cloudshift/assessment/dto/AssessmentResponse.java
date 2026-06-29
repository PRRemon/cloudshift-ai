package com.parthraval.cloudshift.assessment.dto;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentResponse {

    private UUID id;

    private String applicationName;

    private String applicationType;

    private String language;

    private String framework;

    private String database;

    private Integer serverCount;

    private Integer monthlyUsers;

    private Integer storageGb;

    private String trafficPattern;

    private String currentHosting;

    private String additionalRequirements;

    private Instant createdAt;

    private Instant updatedAt;
}