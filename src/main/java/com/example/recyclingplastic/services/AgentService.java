package com.example.recyclingplastic.services;

import com.example.recyclingplastic.dto.request.AgentLoginRequest;
import com.example.recyclingplastic.dto.request.AgentRegistrationRequest;
import com.example.recyclingplastic.dto.request.UpdateAgentRequest;
import com.example.recyclingplastic.dto.response.AgentLoginResponse;
import com.example.recyclingplastic.dto.response.AgentRegistrationResponse;
import com.example.recyclingplastic.dto.response.AgentResponse;
import com.example.recyclingplastic.dto.response.ApiResponse;
import com.example.recyclingplastic.exceptions.*;
import com.github.fge.jackson.jsonpointer.JsonPointerException;

import java.util.List;

public interface AgentService {

    AgentRegistrationResponse register(AgentRegistrationRequest agentRegistrationRequest) throws AgentRegistrationFailedException;

    AgentLoginResponse login(AgentLoginRequest agentLoginRequest) throws AgentNotFoundException, InvalidDetailsException;

    AgentResponse getAgentById(Long Id)throws RecycleException;

    ApiResponse<?> verifyAgent(String token)throws RecycleException, UserNotFoundException;

    List<AgentResponse> getAllAgents(int page, int items);

    ApiResponse<?> deleteAgent(Long Id);

    void deleteAll();

    ApiResponse<?> updateAgentDetails(Long id, UpdateAgentRequest updateAgentRequest) throws UserNotFoundException, IllegalAccessException, JsonPointerException, ProfileUpdateFailedException;
}
