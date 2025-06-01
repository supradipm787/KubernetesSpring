package com.example.check.wisdom.service;

import java.util.List;
import java.util.UUID;

import com.example.check.wisdom.data.entity.Customer;

public interface CustomerService {
  public List<Customer> findCustomerByEmail(String customerEmail);
  public Iterable<Customer> getAllCustomers(); // Method to retrieve all Customers
  public Customer saveCustomer(Customer customer); // Method to save a Customer
  public Customer getCustomerById(UUID customerId); // Method to find a Customer by ID
}
