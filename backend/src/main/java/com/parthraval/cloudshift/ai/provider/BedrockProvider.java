package com.parthraval.cloudshift.ai.provider;

import com.parthraval.cloudshift.ai.dto.AIRequest;
import com.parthraval.cloudshift.ai.dto.AIResponse;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.ContentBlock;
import software.amazon.awssdk.services.bedrockruntime.model.ConverseRequest;
import software.amazon.awssdk.services.bedrockruntime.model.ConverseResponse;
import software.amazon.awssdk.services.bedrockruntime.model.Message;

import java.util.List;

@Service
@Primary
public class BedrockProvider implements AIProvider {

    private final BedrockRuntimeClient bedrockRuntimeClient;

    public BedrockProvider(BedrockRuntimeClient bedrockRuntimeClient) {
        this.bedrockRuntimeClient = bedrockRuntimeClient;
    }

    private static final String MODEL_ID = "google.gemma-3-4b-it";

    @Override
    public AIResponse generate(AIRequest request) {

        String prompt = buildPrompt(request);
        Message userMessage = Message.builder()
                .role("user")
                .content(ContentBlock.builder()
                        .text(prompt)
                        .build())
                .build();

        ConverseRequest converseRequest = ConverseRequest.builder()
                .modelId(MODEL_ID)
                .messages(List.of(userMessage))
                .build();

        ConverseResponse response =
                bedrockRuntimeClient.converse(converseRequest);

        String answer =
                response.output()
                        .message()
                        .content()
                        .getFirst()
                        .text();

        Integer tokens =
                response.usage() != null
                        ? response.usage().totalTokens()
                        : 0;

        AIResponse aiResponse = new AIResponse();
        aiResponse.setProvider("Amazon Bedrock");
        aiResponse.setResponse(answer);
        aiResponse.setTokensUsed(tokens);

        return aiResponse;
    }

    private String buildPrompt(AIRequest request) {

        return request.getPrompt();
    }
}