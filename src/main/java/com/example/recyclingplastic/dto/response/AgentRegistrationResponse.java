package com.example.recyclingplastic.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgentRegistrationResponse {
    private Long id;
    private String message;
}
