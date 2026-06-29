package com.parthraval.cloudshift.recommendation.repository;

import com.parthraval.cloudshift.recommendation.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecommendationRepository extends JpaRepository<Recommendation, UUID> {

    List<Recommendation> findByAssessmentId(UUID assessmentId);

    Optional<Recommendation> findFirstByAssessmentIdOrderByCreatedAtDesc(UUID assessmentId);

}