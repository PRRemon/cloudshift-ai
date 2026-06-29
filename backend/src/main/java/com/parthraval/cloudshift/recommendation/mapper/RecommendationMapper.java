package com.parthraval.cloudshift.recommendation.mapper;

import com.parthraval.cloudshift.ai.dto.AIResponse;
import com.parthraval.cloudshift.assessment.entity.Assessment;
import com.parthraval.cloudshift.recommendation.dto.RecommendationResponse;
import com.parthraval.cloudshift.recommendation.dto.RecommendationResult;
import com.parthraval.cloudshift.recommendation.entity.Recommendation;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Component
public class RecommendationMapper {

    public Recommendation toEntity(
            Assessment assessment,
            RecommendationResult result,
            AIResponse aiResponse) {

        return Recommendation.builder()
                .assessmentId(assessment.getId())
                .provider(aiResponse.getProvider())

                .compute(result.getCompute())
                .database(result.getDatabase())
                .storage(result.getStorage())
                .networking(result.getNetworking())
                .estimatedMonthlyCost(result.getEstimatedMonthlyCost())
                .migrationComplexity(result.getMigrationComplexity())

                .securityRecommendations(
                        result.getSecurityRecommendations() == null
                                ? ""
                                : String.join("\n", result.getSecurityRecommendations()))

                .risks(
                        result.getRisks() == null
                                ? ""
                                : String.join("\n", result.getRisks()))

                .rawAiResponse(aiResponse.getResponse())

                .tokensUsed(aiResponse.getTokensUsed())

                .createdAt(Instant.now())

                .build();
    }

    public RecommendationResponse toResponse(Recommendation recommendation) {

        List<String> security =
                recommendation.getSecurityRecommendations() == null ||
                        recommendation.getSecurityRecommendations().isBlank()
                        ? List.of()
                        : Arrays.asList(
                        recommendation.getSecurityRecommendations().split("\n"));

        List<String> risks =
                recommendation.getRisks() == null ||
                        recommendation.getRisks().isBlank()
                        ? List.of()
                        : Arrays.asList(
                        recommendation.getRisks().split("\n"));

        return RecommendationResponse.builder()
                .id(recommendation.getId())
                .assessmentId(recommendation.getAssessmentId())
                .provider(recommendation.getProvider())

                .compute(recommendation.getCompute())
                .database(recommendation.getDatabase())
                .storage(recommendation.getStorage())
                .networking(recommendation.getNetworking())
                .estimatedMonthlyCost(recommendation.getEstimatedMonthlyCost())
                .migrationComplexity(recommendation.getMigrationComplexity())

                .securityRecommendations(security)
                .risks(risks)

                .tokensUsed(recommendation.getTokensUsed())
                .createdAt(recommendation.getCreatedAt())

                .build();
    }

}

