package com.parthraval.cloudshift.assessment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "assessment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
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

    @Column(length = 5000)
    private String additionalRequirements;

    private Instant createdAt;

    private Instant updatedAt;
}