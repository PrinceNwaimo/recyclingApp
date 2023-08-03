package com.example.recyclingplastic.services.Implementation;

import com.example.recyclingplastic.dto.request.CustomerRequest;
import com.example.recyclingplastic.dto.response.CustomerResponse;
import com.example.recyclingplastic.exceptions.CustomerNotFoundException;
import com.example.recyclingplastic.models.Customer;
import com.example.recyclingplastic.models.Role;
import com.example.recyclingplastic.repository.CustomerRepository;
import com.example.recyclingplastic.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerRequest createCustomer(CustomerRequest customerDto) {
        Customer customer = new Customer();
        customer.setFirst_name(customerDto.getFirst_name());
        customer.setLast_name(customerDto.getLast_name());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setUsername(customerDto.getUsername());
        customer.setRole(Role.CUSTOMER);
        Customer newCustomer = customerRepository.save(customer);

        CustomerRequest customerResponse = new CustomerRequest();
        customerResponse.setCustomerId(newCustomer.getCustomerId());
        customerResponse.setFirst_name(newCustomer.getFirst_name());
        customerResponse.setLast_name(newCustomer.getLast_name());
        customerResponse.setEmail(newCustomer.getEmail());
        customerResponse.setPassword(newCustomer.getPassword());
        customerResponse.setUsername(newCustomer.getUsername());
        return customerResponse;

    }

    @Override
    public CustomerResponse getAllCustomer(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Customer> customers = customerRepository.findAll(pageable);
        List<Customer> listOfCustomer = customers.getContent();
        List<CustomerRequest> content = listOfCustomer.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setContent(content);
        customerResponse.setPageNo(customers.getNumber());
        customerResponse.setPageSize(customers.getSize());
        customerResponse.setTotalElements(customers.getTotalElements());
        customerResponse.setTotalPages(customers.getTotalPages());
        customerResponse.setLast(customers.isLast());
        return customerResponse;
    }

    @Override
    public CustomerRequest getCustomerById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("customer could not be found"));
        return mapToDto(customer);
    }

    @Override
    public CustomerRequest updateCustomer(CustomerRequest customerDto, long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("customer could not be updated"));

        customer.setFirst_name(customerDto.getEmail());
        customer.setLast_name(customerDto.getLast_name());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setUsername(customerDto.getUsername());

        Customer updateCustomer = customerRepository.save(customer);
        return mapToDto(updateCustomer);
    }

    @Override
    public void deleteCustomerId(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer could not be deleted"));
        customerRepository.delete(customer);
    }

    private CustomerRequest mapToDto(Customer customer){
        CustomerRequest customerDto = new CustomerRequest();
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setFirst_name(customer.getFirst_name());
        customerDto.setLast_name(customer.getLast_name());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPassword(customer.getPassword());
        customerDto.setUsername(customer.getUsername());
        return customerDto;
    }
}
