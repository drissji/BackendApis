package net.spring.clientmanager_testing_microservices.repository;

import net.spring.clientmanager_testing_microservices.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByEmail(String email);
    List<Customer> findByFirstNameContainingIgnoreCase(String Keyword);

}
