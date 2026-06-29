package com.parthraval.cloudshift.ai.prompt;

import com.parthraval.cloudshift.ai.dto.AIRequest;
import com.parthraval.cloudshift.assessment.entity.Assessment;
import org.springframework.stereotype.Component;

@Component
public class AssessmentPromptBuilder {

    public AIRequest build(Assessment assessment) {

        return AIRequest.builder()
                .applicationName(assessment.getApplicationName())
                .applicationType(assessment.getApplicationType())
                .language(assessment.getLanguage())
                .framework(assessment.getFramework())
                .database(assessment.getDatabase())
                .serverCount(assessment.getServerCount())
                .monthlyUsers(assessment.getMonthlyUsers())
                .storageGb(assessment.getStorageGb())
                .trafficPattern(assessment.getTrafficPattern())
                .currentHosting(assessment.getCurrentHosting())
                .additionalRequirements(assessment.getAdditionalRequirements())
                .build();
    }
}
