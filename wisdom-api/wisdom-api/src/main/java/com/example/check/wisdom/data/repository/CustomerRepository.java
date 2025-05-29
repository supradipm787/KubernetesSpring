package com.example.check.wisdom.data.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.check.wisdom.data.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    // Custom query methods can be defined here if needed
    // For example, to find customers by email:
    Optional<Customer> findByEmail(String email);
    
    // Additional methods can be added as required

}
