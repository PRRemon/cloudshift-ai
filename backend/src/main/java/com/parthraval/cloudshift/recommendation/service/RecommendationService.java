package com.parthraval.cloudshift.recommendation.service;

import com.parthraval.cloudshift.ai.dto.AIRequest;
import com.parthraval.cloudshift.ai.dto.AIResponse;
import com.parthraval.cloudshift.ai.prompt.AssessmentPromptBuilder;
import com.parthraval.cloudshift.ai.service.AIService;
import com.parthraval.cloudshift.assessment.entity.Assessment;
import com.parthraval.cloudshift.assessment.repository.AssessmentRepository;
import com.parthraval.cloudshift.recommendation.dto.RecommendationResponse;
import com.parthraval.cloudshift.recommendation.entity.Recommendation;
import com.parthraval.cloudshift.recommendation.mapper.RecommendationMapper;
import com.parthraval.cloudshift.recommendation.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final AssessmentRepository assessmentRepository;
    private final RecommendationRepository recommendationRepository;
    private final RecommendationMapper recommendationMapper;
    private final AssessmentPromptBuilder promptBuilder;
    private final AIService aiService;

    public RecommendationResponse generateRecommendation(UUID assessmentId) {
        Assessment assessment = assessmentRepository.findById(assessmentId).orElseThrow(()
                -> new RuntimeException("Assessment not found: " + assessmentId));
        AIRequest aiRequest = promptBuilder.build(assessment);
        AIResponse aiResponse = aiService.generate(aiRequest);
        Recommendation recommendation = recommendationMapper.toEntity(assessment, aiResponse);
        Recommendation saved = recommendationRepository.save(recommendation);
        return recommendationMapper.toResponse(saved);
    }
}
