package com.parthraval.cloudshift.ai.prompt;


import com.parthraval.cloudshift.assessment.entity.Assessment;
import org.springframework.stereotype.Component;

@Component
public class AssessmentPromptBuilder
        implements PromptBuilder<Assessment> {

    @Override
    public String build(Assessment assessment) {

        return """
                You are a Senior AWS Solutions Architect.
                
                Analyze the following application.
                
                Application Name: %s
                Application Type: %s
                Language: %s
                Framework: %s
                Database: %s
                Server Count: %d
                Monthly Users: %d
                Storage (GB): %d
                Traffic Pattern: %s
                Current Hosting: %s
                Additional Requirements: %s
                
                Return ONLY valid JSON.
                
                Your response MUST start with {
                
                Your response MUST end with }
                
                Do NOT include markdown.
                
                Do NOT include explanations.
                
                Do NOT include headings.
                
                Return EXACTLY this JSON schema:
                
                {
                  "compute":"",
                  "database":"",
                  "storage":"",
                  "networking":"",
                  "estimatedMonthlyCost":"",
                  "migrationComplexity":"",
                  "securityRecommendations":[],
                  "risks":[]
                }
                """.formatted(
                assessment.getApplicationName(),
                assessment.getApplicationType(),
                assessment.getLanguage(),
                assessment.getFramework(),
                assessment.getDatabase(),
                assessment.getServerCount(),
                assessment.getMonthlyUsers(),
                assessment.getStorageGb(),
                assessment.getTrafficPattern(),
                assessment.getCurrentHosting(),
                assessment.getAdditionalRequirements()
        );
    }
}
