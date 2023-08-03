package com.example.recyclingplastic.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AgentLoginRequest {
        @JsonProperty("email")
        private String email;

    @JsonProperty("username")
    private String username;

        @JsonProperty("password")
        private String password;
}
