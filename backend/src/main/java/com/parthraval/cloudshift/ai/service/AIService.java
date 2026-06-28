package com.parthraval.cloudshift.ai.service;

import com.parthraval.cloudshift.ai.dto.AIRequest;
import com.parthraval.cloudshift.ai.dto.AIResponse;
import com.parthraval.cloudshift.ai.provider.AIProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIService {

    private final AIProvider aiProvider;

    public AIResponse generate(AIRequest request) {
        return aiProvider.generate(request);
    }

}