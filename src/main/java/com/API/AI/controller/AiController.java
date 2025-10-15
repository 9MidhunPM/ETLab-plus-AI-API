package com.API.AI.controller;

import com.API.AI.dto.AiQueryRequest;
import com.API.AI.dto.AiQueryResponse;
import com.API.AI.service.GeminiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class AiController {
    
    private final GeminiService geminiService;

    @PostMapping(value = "/ai-query", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AiQueryResponse> aiQuery(@RequestBody AiQueryRequest request) {
        try {
            log.info("Received AI query request with {} data items", 
                    request.getData() != null ? request.getData().size() : 0);
            
            // Validate request
            if (request.getData() == null || request.getData().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(AiQueryResponse.error("Data field is required and cannot be empty"));
            }
            
            if (request.getQuery() == null || request.getQuery().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(AiQueryResponse.error("Query field is required and cannot be empty"));
            }

            // Query Gemini
            String response = geminiService.queryGemini(request);
            
            return ResponseEntity.ok(AiQueryResponse.success(response, "gemini"));
            
        } catch (Exception e) {
            log.error("Error processing AI query", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(AiQueryResponse.error("Failed to process query: " + e.getMessage()));
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("AI API is running");
    }

    @GetMapping("/models")
    public ResponseEntity<?> getModels() {
        return ResponseEntity.ok(geminiService.getAvailableModels());
    }
}
