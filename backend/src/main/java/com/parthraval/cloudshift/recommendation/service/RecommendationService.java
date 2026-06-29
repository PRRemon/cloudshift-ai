package com.parthraval.cloudshift.recommendation.service;

import com.parthraval.cloudshift.ai.dto.AIRequest;
import com.parthraval.cloudshift.ai.dto.AIResponse;
import com.parthraval.cloudshift.ai.prompt.AssessmentPromptBuilder;
import com.parthraval.cloudshift.ai.service.AIService;
import com.parthraval.cloudshift.assessment.entity.Assessment;
import com.parthraval.cloudshift.assessment.repository.AssessmentRepository;
import com.parthraval.cloudshift.common.exception.ResourceNotFoundException;
import com.parthraval.cloudshift.recommendation.dto.RecommendationResponse;
import com.parthraval.cloudshift.recommendation.dto.RecommendationResult;
import com.parthraval.cloudshift.recommendation.entity.Recommendation;
import com.parthraval.cloudshift.recommendation.mapper.RecommendationMapper;
import com.parthraval.cloudshift.recommendation.parser.RecommendationParser;
import com.parthraval.cloudshift.recommendation.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationService {

    private final AssessmentRepository assessmentRepository;

    private final RecommendationRepository recommendationRepository;

    private final RecommendationMapper recommendationMapper;

    private final AssessmentPromptBuilder promptBuilder;

    private final RecommendationParser recommendationParser;

    private final AIService aiService;

    public RecommendationResponse generateRecommendation(UUID assessmentId) {

        Optional<Recommendation> cachedRecommendation =
                recommendationRepository
                        .findFirstByAssessmentIdOrderByCreatedAtDesc(assessmentId);

        if (cachedRecommendation.isPresent()) {

            log.info("Returning cached recommendation for assessment {}", assessmentId);

            return recommendationMapper.toResponse(cachedRecommendation.get());
        }

        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Assessment not found : " + assessmentId));

        String prompt = promptBuilder.build(assessment);

        AIRequest aiRequest = AIRequest.builder()
                .prompt(prompt)
                .build();

        AIResponse aiResponse = aiService.generate(aiRequest);

        RecommendationResult recommendationResult =
                recommendationParser.parse(aiResponse.getResponse());

        Recommendation recommendation =
                recommendationMapper.toEntity(
                        assessment,
                        recommendationResult,
                        aiResponse);

        Recommendation saved =
                recommendationRepository.save(recommendation);

        return recommendationMapper.toResponse(saved);
    }
}
