package com.parthraval.cloudshift.ai.parser;

import org.springframework.stereotype.Component;

@Component
public class AIResponseCleaner {

    public String clean(String response) {

        if (response == null || response.isBlank()) {
            throw new RuntimeException("AI response is empty");
        }

        int start = response.indexOf('{');
        int end = response.lastIndexOf('}');

        if (start == -1 || end == -1 || start > end) {
            throw new RuntimeException("Unable to locate JSON object in AI response.");
        }

        return response.substring(start, end + 1).trim();
    }
}