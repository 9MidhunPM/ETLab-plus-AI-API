package com.API.AI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiQueryResponse {
    private String response;
    private String model;
    private boolean success;
    private String error;

    public static AiQueryResponse success(String response, String model) {
        AiQueryResponse result = new AiQueryResponse();
        result.setResponse(response);
        result.setModel(model);
        result.setSuccess(true);
        return result;
    }

    public static AiQueryResponse error(String error) {
        AiQueryResponse result = new AiQueryResponse();
        result.setError(error);
        result.setSuccess(false);
        return result;
    }
}
