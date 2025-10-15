package com.API.AI.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiQueryRequest {
    // Accept data as raw JSON to handle any structure
    private List<JsonNode> data;
    private String query;
}
