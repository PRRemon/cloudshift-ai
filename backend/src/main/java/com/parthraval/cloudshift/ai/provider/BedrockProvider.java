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

        return """
                You are an AWS Solutions Architect.
                
                Analyze the following application.
                
                Application Name: %s
                
                Application Type: %s
                
                Language: %s
                
                Framework: %s
                
                Database: %s
                
                Servers: %d
                
                Monthly Users: %d
                
                Storage: %d GB
                
                Traffic Pattern: %s
                
                Current Hosting: %s
                
                Additional Requirements:
                %s
                
                Recommend:
                
                1. Compute
                2. Database
                3. Storage
                4. Networking
                5. Security
                6. Estimated Monthly Cost
                7. Migration Complexity
                
                Return the answer in markdown.
                """
                .formatted(
                        request.getApplicationName(),
                        request.getApplicationType(),
                        request.getLanguage(),
                        request.getFramework(),
                        request.getDatabase(),
                        request.getServerCount(),
                        request.getMonthlyUsers(),
                        request.getStorageGb(),
                        request.getTrafficPattern(),
                        request.getCurrentHosting(),
                        request.getAdditionalRequirements());

    }
}