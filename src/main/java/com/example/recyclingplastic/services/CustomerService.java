package com.example.recyclingplastic.services;

import com.example.recyclingplastic.dto.request.CustomerRequest;
import com.example.recyclingplastic.dto.response.CustomerResponse;

public interface CustomerService {
    CustomerRequest createCustomer(CustomerRequest customerDto);
    CustomerResponse getAllCustomer(int pageNo, int pageSize);
    CustomerRequest getCustomerById(long id);
    CustomerRequest updateCustomer(CustomerRequest customerDto, long id);
    void deleteCustomerId(long id);
}