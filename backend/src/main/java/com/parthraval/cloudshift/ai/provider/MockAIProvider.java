package com.parthraval.cloudshift.ai.provider;

import com.parthraval.cloudshift.ai.dto.AIRequest;
import com.parthraval.cloudshift.ai.dto.AIResponse;
import org.springframework.stereotype.Service;

@Service
public class MockAIProvider implements AIProvider {

    @Override
    public AIResponse generate(AIRequest request) {

        AIResponse response = new AIResponse();
        response.setProvider("Mock AI");
        response.setResponse("Hello from CloudShift AI");
        response.setTokensUsed(42);

        return response;
    }

}