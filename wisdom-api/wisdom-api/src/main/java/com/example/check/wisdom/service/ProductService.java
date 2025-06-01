package com.example.check.wisdom.service;

import java.util.List;
import java.util.UUID;

import com.example.check.wisdom.data.entity.Customer;
import com.example.check.wisdom.data.entity.Product;
//import com.example.check.wisdom.data.entity.Vendor;

public interface ProductService {

  public List<Product> findProductByName(String productName); // Method to find Products by name
  public Iterable<Product> getAllProducts(); // Method to retrieve all Products
  public Product saveProduct(Product product); // Method to save a Product
  public Product getProductById(UUID productId); // Method to find a Product by ID
  

}
