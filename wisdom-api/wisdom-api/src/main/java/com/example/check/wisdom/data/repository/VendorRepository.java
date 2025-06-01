package com.example.check.wisdom.data.repository;

import java.util.Optional;
import java.util.UUID;

//import com.example.check.wisdom.data.entity.Customer;
import com.example.check.wisdom.data.entity.Vendor;

import org.springframework.data.jpa.repository.JpaRepository;



public interface VendorRepository extends JpaRepository <Vendor, UUID> {
  Optional<Vendor> findByEmail(String email);
  //Optional<Vendor> findByContact(String contact);
}
