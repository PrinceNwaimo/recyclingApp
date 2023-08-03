package com.example.recyclingplastic.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private Long customerId;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String username;
}

