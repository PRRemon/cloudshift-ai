package com.parthraval.cloudshift.common.controller;

import com.parthraval.cloudshift.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> health() {

        Map<String, Object> data = Map.of(
                "application", "CloudShift AI",
                "status", "UP",
                "time", Instant.now()
        );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Application is running",
                        data
                )
        );
    }

    @GetMapping("/error")
    public ResponseEntity<ApiResponse<Void>> error() {
        throw new RuntimeException("This is a demo exception.");
    }

    @GetMapping("/hello/{name}")
    public ResponseEntity<ApiResponse<String>> hello(
            @PathVariable String name
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        "Greeting generated successfully",
                        "Hello, " + name + "!"
                )
        );
    }
}