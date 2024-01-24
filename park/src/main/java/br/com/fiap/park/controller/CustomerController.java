package br.com.fiap.park.controller;

import br.com.fiap.park.dto.CustomerRequest;
import br.com.fiap.park.model.Customer;
import br.com.fiap.park.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("create")
    @Transactional
    public Customer create(@RequestBody CustomerRequest customerRequest){
        return customerService.toModel(customerRequest);
    }
}
