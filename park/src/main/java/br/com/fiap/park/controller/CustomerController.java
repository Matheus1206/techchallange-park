package br.com.fiap.park.controller;

import br.com.fiap.park.dto.CustomerRequest;
import br.com.fiap.park.model.Customer;
import br.com.fiap.park.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("create")
    public Customer create(@RequestBody CustomerRequest customerRequest){
        return customerService.toModel(customerRequest);
    }

    @GetMapping("{cpf}")
    public Customer get(@PathVariable String cpf){
        return customerService.getCustomer(cpf);
    }
}
