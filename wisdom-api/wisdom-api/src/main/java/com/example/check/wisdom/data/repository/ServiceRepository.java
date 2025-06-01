package com.example.check.wisdom.data.repository;
import com.example.check.wisdom.data.entity.Service;

import java.util.UUID;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

//import com.example.check.wisdom.data.entity.Vendor;

public interface ServiceRepository extends JpaRepository <Service, UUID> {
  Optional<Service> findByName(String servicename);
}
