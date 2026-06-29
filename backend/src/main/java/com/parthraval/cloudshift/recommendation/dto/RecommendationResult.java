package com.parthraval.cloudshift.recommendation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationResult {

    private String compute;

    private String database;

    private String storage;

    private String networking;

    private String estimatedMonthlyCost;

    private String migrationComplexity;

    private List<String> securityRecommendations;

    private List<String> risks;
}
