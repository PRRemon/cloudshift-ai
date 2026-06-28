package com.parthraval.cloudshift.ai.controller;

import com.parthraval.cloudshift.ai.dto.AIRequest;
import com.parthraval.cloudshift.ai.dto.AIResponse;
import com.parthraval.cloudshift.ai.service.AIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/test")
    public AIResponse test(@RequestBody AIRequest request) {
        return aiService.generate(request);
    }

}