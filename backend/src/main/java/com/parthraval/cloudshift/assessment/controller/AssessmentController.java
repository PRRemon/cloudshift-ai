package com.parthraval.cloudshift.assessment.controller;

import com.parthraval.cloudshift.assessment.dto.AssessmentRequest;
import com.parthraval.cloudshift.assessment.dto.AssessmentResponse;
import com.parthraval.cloudshift.assessment.service.AssessmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/assessments")
@RequiredArgsConstructor
public class AssessmentController {

    private final AssessmentService assessmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssessmentResponse createAssessment(@Valid @RequestBody AssessmentRequest request) {
        return assessmentService.create(request);
    }

    @GetMapping
    public List<AssessmentResponse> getAllAssessments() {
        return assessmentService.findAll();
    }

    @GetMapping("/{id}")
    public AssessmentResponse getAssessment(@PathVariable UUID id) {
        return assessmentService.findById(id);
    }

    @PutMapping("/{id}")
    public AssessmentResponse updateAssessment(@PathVariable UUID id, @Valid @RequestBody AssessmentRequest request) {
        return assessmentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAssessment(@PathVariable UUID id) {
        assessmentService.delete(id);
    }


}