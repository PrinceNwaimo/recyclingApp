package com.example.recyclingplastic.controller;

import com.example.recyclingplastic.dto.request.CustomerRequest;
import com.example.recyclingplastic.dto.response.CustomerResponse;
import com.example.recyclingplastic.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(("customer/create"))
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerRequest> createCustomer(@RequestBody CustomerRequest request) {
        return  new ResponseEntity<>(customerService.createCustomer(request), HttpStatus.CREATED);
    }

    @GetMapping("customers")
    public ResponseEntity<CustomerResponse> getCustomers(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return new ResponseEntity<>(customerService.getAllCustomer(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping( "customer/{id}")
    public ResponseEntity<CustomerRequest> customerDetail(@PathVariable long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PutMapping("customer/{id}/update")
    public ResponseEntity<CustomerRequest> updateCustomer(@RequestBody CustomerRequest customerDto, @PathVariable("id") long customerId) {
        CustomerRequest response = customerService.updateCustomer(customerDto, customerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("customer/{id}/delete")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") long customerId) {
        customerService.deleteCustomerId(customerId);
        return new ResponseEntity<>("customer delete", HttpStatus.OK);
    }
}
