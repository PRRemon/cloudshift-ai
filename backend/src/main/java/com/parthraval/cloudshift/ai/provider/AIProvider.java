package com.parthraval.cloudshift.ai.provider;

import com.parthraval.cloudshift.ai.dto.AIRequest;
import com.parthraval.cloudshift.ai.dto.AIResponse;

public interface AIProvider {

    AIResponse generate(AIRequest request);

}