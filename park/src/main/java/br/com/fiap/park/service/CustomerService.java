package br.com.fiap.park.service;

import br.com.fiap.park.dto.request.CustomerRequest;
import br.com.fiap.park.model.Customer;
import br.com.fiap.park.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public Customer toModel(CustomerRequest customerRequest) {
        Customer customer = new Customer(customerRequest.nome(), customerRequest.cpf());
        customerRepository.save(customer);
        return customer;
    }
}
