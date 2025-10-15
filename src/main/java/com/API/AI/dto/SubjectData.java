package com.API.AI.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectData {
    // Original fields for exam results
    private String subjectName;
    private String subjectCode;
    private String semester;
    private String marksObtained;
    private String maximumMarks;
    private String exam;
    
    // New fields for attendance
    private String subject_code;
    private String attendance_percentage;
    private String present_hours;
    private String total_hours;
    
    // Store any additional fields that might come in
    private Map<String, Object> additionalProperties = new HashMap<>();
    
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        additionalProperties.put(name, value);
    }
    
    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
}
