package com.parthraval.cloudshift.assessment.service;

import com.parthraval.cloudshift.assessment.dto.AssessmentRequest;
import com.parthraval.cloudshift.assessment.dto.AssessmentResponse;
import com.parthraval.cloudshift.assessment.entity.Assessment;
import com.parthraval.cloudshift.assessment.mapper.AssessmentMapper;
import com.parthraval.cloudshift.assessment.repository.AssessmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssessmentService {

    private final AssessmentRepository repository;

    private final AssessmentMapper mapper;

    public AssessmentResponse create(AssessmentRequest request) {
        Assessment assessment = mapper.toEntity(request);
        Assessment saved = repository.save(assessment);
        return mapper.toResponse(saved);
    }

    public List<AssessmentResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public AssessmentResponse findById(UUID id) {
        Assessment assessment = repository.findById(id).orElseThrow(() -> new RuntimeException("Assessment not found : " + id));
        return mapper.toResponse(assessment);
    }

    public AssessmentResponse update(UUID id, AssessmentRequest request) {
        Assessment assessment = repository.findById(id).orElseThrow(() -> new RuntimeException("Assessment not found : " + id));
        assessment.setApplicationName(request.getApplicationName());
        assessment.setApplicationType(request.getApplicationType());
        assessment.setLanguage(request.getLanguage());
        assessment.setFramework(request.getFramework());
        assessment.setDatabase(request.getDatabase());
        assessment.setServerCount(request.getServerCount());
        assessment.setMonthlyUsers(request.getMonthlyUsers());
        assessment.setStorageGb(request.getStorageGb());
        assessment.setTrafficPattern(request.getTrafficPattern());
        assessment.setCurrentHosting(request.getCurrentHosting());
        assessment.setAdditionalRequirements(request.getAdditionalRequirements());
        assessment.setUpdatedAt(Instant.now());
        Assessment updated = repository.save(assessment);
        return mapper.toResponse(updated);
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Assessment not found : " + id);
        }
        repository.deleteById(id);
    }

}