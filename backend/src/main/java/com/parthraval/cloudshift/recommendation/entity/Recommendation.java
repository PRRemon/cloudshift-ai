package com.parthraval.cloudshift.recommendation.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "recommendation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID assessmentId;

    @Column(nullable = false)
    private String provider;

    private String compute;

    @Column(name = "database_name")
    private String database;

    private String storage;

    private String networking;

    private String estimatedMonthlyCost;

    private String migrationComplexity;

    @Column(columnDefinition = "TEXT")
    private String securityRecommendations;

    @Column(columnDefinition = "TEXT")
    private String risks;

    @Column(columnDefinition = "TEXT")
    private String rawAiResponse;

    private Integer tokensUsed;

    private Instant createdAt;

}