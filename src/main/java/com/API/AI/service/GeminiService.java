package com.API.AI.service;

import com.API.AI.config.GeminiConfig;
import com.API.AI.dto.AiQueryRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeminiService {
    private final WebClient geminiWebClient;
    private final GeminiConfig geminiConfig;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final List<String> AVAILABLE_MODELS = Arrays.asList(
            "gemini-2.0-flash",
            "gemini-2.0-flash-lite",
            "gemini-1.5-flash"
    );

    public String queryGemini(AiQueryRequest request) {
        try {
            String prompt = buildPrompt(request);
            
            for (String model : AVAILABLE_MODELS) {
                try {
                    String response = callGeminiApi(prompt, model);
                    if (response != null && !response.isEmpty()) {
                        return response;
                    }
                } catch (Exception e) {
                    log.warn("Model {} failed: {}", model, e.getMessage());
                }
            }
            
            throw new RuntimeException("All models failed to respond");
            
        } catch (Exception e) {
            log.error("Error querying Gemini API", e);
            throw new RuntimeException("Failed to get response from Gemini: " + e.getMessage());
        }
    }

    private String buildPrompt(AiQueryRequest request) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("You are an intelligent assistant helping students analyze their academic data.\n\n");
        prompt.append("Based on the following data:\n\n");
        
        // Convert data to readable format
        try {
            String jsonData = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(request.getData());
            prompt.append(jsonData);
        } catch (Exception e) {
            prompt.append(request.getData().toString());
        }
        
        prompt.append("\n\nUser Query: ").append(request.getQuery());
        prompt.append("\n\nPlease analyze the data carefully and provide a helpful, detailed, and actionable response.");
        prompt.append("\nIf the data contains attendance information, focus on attendance patterns.");
        prompt.append("\nIf the data contains exam results, focus on academic performance.");
        prompt.append("\nProvide specific recommendations and insights based on the data provided.");
        
        return prompt.toString();
    }

    private String callGeminiApi(String prompt, String model) {
        // Build request body for Gemini API
        Map<String, Object> requestBody = new HashMap<>();
        
        List<Map<String, Object>> contents = new ArrayList<>();
        Map<String, Object> content = new HashMap<>();
        
        List<Map<String, String>> parts = new ArrayList<>();
        Map<String, String> part = new HashMap<>();
        part.put("text", prompt);
        parts.add(part);
        
        content.put("parts", parts);
        contents.add(content);
        
        requestBody.put("contents", contents);

        // Make API call
        String response = geminiWebClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/models/" + model + ":generateContent")
                        .queryParam("key", geminiConfig.getKey())
                        .build())
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Log raw JSON response
        log.info("Raw Gemini API Response: {}", response);

        // Parse response
        return parseGeminiResponse(response);
    }

    private String parseGeminiResponse(String responseJson) {
        try {
            JsonNode root = objectMapper.readTree(responseJson);
            JsonNode candidates = root.path("candidates");
            
            if (candidates.isArray() && candidates.size() > 0) {
                JsonNode firstCandidate = candidates.get(0);
                JsonNode content = firstCandidate.path("content");
                JsonNode parts = content.path("parts");
                
                if (parts.isArray() && parts.size() > 0) {
                    JsonNode firstPart = parts.get(0);
                    return firstPart.path("text").asText();
                }
            }
            
            return "No response generated";
        } catch (Exception e) {
            log.error("Error parsing Gemini response", e);
            return "Error parsing response: " + e.getMessage();
        }
    }

    public List<String> getAvailableModels() {
        return AVAILABLE_MODELS;
    }
}
