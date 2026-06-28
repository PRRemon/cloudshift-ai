package com.parthraval.cloudshift.ai.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AIRequest {

    private String prompt;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}