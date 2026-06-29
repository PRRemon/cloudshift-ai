package com.parthraval.cloudshift.recommendation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationResponse {

    private UUID id;

    private UUID assessmentId;

    private String provider;

    private String compute;

    private String database;

    private String storage;

    private String networking;

    private String estimatedMonthlyCost;

    private String migrationComplexity;

    private List<String> securityRecommendations;

    private List<String> risks;

    private Integer tokensUsed;

    private Instant createdAt;

}
