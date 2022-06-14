package com.kpi.model.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpi.model.entities.Enrollee;

import java.util.List;

public class JSONConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String enrolleeListToJson(List<Enrollee> enrollees) throws JsonProcessingException {
        return objectMapper.writeValueAsString(enrollees);
    }


    public static List<Enrollee> jsonToEnrolleeList(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, new TypeReference<>(){});
    }
}
