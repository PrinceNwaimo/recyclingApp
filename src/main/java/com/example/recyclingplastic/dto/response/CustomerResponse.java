package com.example.recyclingplastic.dto.response;

import com.example.recyclingplastic.dto.request.CustomerRequest;
import lombok.Data;

import java.util.List;

@Data
public class CustomerResponse {
    private List<CustomerRequest> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}