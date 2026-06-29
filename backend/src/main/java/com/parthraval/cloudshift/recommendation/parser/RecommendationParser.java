package com.parthraval.cloudshift.recommendation.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parthraval.cloudshift.ai.parser.AIResponseCleaner;
import com.parthraval.cloudshift.recommendation.dto.RecommendationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecommendationParser {

    private final ObjectMapper objectMapper;
    private final AIResponseCleaner cleaner;

    public RecommendationResult parse(String response) {

        try {

            String json = cleaner.clean(response);

            return objectMapper.readValue(
                    json,
                    RecommendationResult.class);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to parse AI recommendation.",
                    e);

        }

    }

}