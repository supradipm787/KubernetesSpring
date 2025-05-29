package com.example.check.wisdom.service;

import java.util.List;

import com.example.check.wisdom.data.entity.Customer;

public interface CustomerService {
  public List<Customer> findCustomerByEmail(String email);
  public Iterable<Customer> getAllCustomers(); // Method to retrieve all customers
}
