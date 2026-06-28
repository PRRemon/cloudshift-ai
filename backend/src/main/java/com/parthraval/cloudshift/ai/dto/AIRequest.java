package com.parthraval.cloudshift.ai.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AIRequest {

    private String applicationName;

    private String applicationType;

    private String language;

    private String framework;

    private String database;

    private Integer serverCount;

    private Integer monthlyUsers;

    private Integer storageGb;

    private String trafficPattern;

    private String currentHosting;

    private String additionalRequirements;


}