package com.parthraval.cloudshift.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AIResponse {

    private String provider;

    private String response;

    private Integer tokensUsed;

    private Instant generatedAt;

}