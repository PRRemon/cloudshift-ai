package com.parthraval.cloudshift.assessment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentRequest {

    @NotBlank
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
}