package com.example.check.wisdom.data.repository;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.check.wisdom.data.entity.Product;

public interface ProductRepository extends JpaRepository <Product, UUID> {

  Optional<Product> findByName(String name);

}