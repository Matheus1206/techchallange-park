package br.com.fiap.park.repository;

import br.com.fiap.park.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByCpf(String cpf);
}
