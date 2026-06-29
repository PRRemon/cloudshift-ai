package com.parthraval.cloudshift.ai.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;

@Configuration
public class BedrockClientConfig {

    @Bean
    public BedrockRuntimeClient bedrockRuntimeClient() {

        return BedrockRuntimeClient.builder()
                .region(Region.AP_SOUTH_1)
                .build();

    }

}