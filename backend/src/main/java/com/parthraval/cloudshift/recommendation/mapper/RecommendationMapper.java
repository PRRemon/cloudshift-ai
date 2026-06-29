package com.parthraval.cloudshift.recommendation.mapper;

import com.parthraval.cloudshift.ai.dto.AIResponse;
import com.parthraval.cloudshift.assessment.entity.Assessment;
import com.parthraval.cloudshift.recommendation.dto.RecommendationResponse;
import com.parthraval.cloudshift.recommendation.entity.Recommendation;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class RecommendationMapper {

    public Recommendation toEntity(Assessment assessment,
                                   AIResponse aiResponse) {

        return Recommendation.builder()
                .assessmentId(assessment.getId())
                .provider(aiResponse.getProvider())
                .recommendation(aiResponse.getResponse())
                .rawAiResponse(aiResponse.getResponse())
                .tokensUsed(aiResponse.getTokensUsed())
                .createdAt(Instant.now())
                .build();
    }

    public RecommendationResponse toResponse(Recommendation recommendation) {

        return RecommendationResponse.builder()
                .id(recommendation.getId())
                .assessmentId(recommendation.getAssessmentId())
                .provider(recommendation.getProvider())
                .recommendation(recommendation.getRecommendation())
                .tokensUsed(recommendation.getTokensUsed())
                .createdAt(recommendation.getCreatedAt())
                .build();
    }
}
