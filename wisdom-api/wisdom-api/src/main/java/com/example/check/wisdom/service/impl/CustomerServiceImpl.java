package com.example.check.wisdom.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.check.wisdom.data.entity.Customer;
import com.example.check.wisdom.data.repository.CustomerRepository;
import com.example.check.wisdom.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service
public class CustomerServiceImpl implements CustomerService{
  
  @Autowired
  private CustomerRepository customerRepository;
  
  @Override
  public List<Customer> findCustomerByEmail(String email){
    List <Customer> customers = new ArrayList<Customer>();
    log.info("In find Customer By Email");
    if (StringUtils.hasLength(email)) {
      Optional<Customer> customer = customerRepository.findByEmail(email);
      if (customer.isPresent()) {
        customers.add(customer.get());
      } 
      return customers;
    } 

    return customers;
  }
  
  //get All Customers
  @Override
  public Iterable<Customer> getAllCustomers() {
    log.info("In all get customers in ServiceImpl");
    return this.customerRepository.findAll();
  }

  //save Customer
  @Override
  public com.example.check.wisdom.data.entity.Customer saveCustomer(Customer customer) {
    log.info("In save Customer in ServiceImpl");
    
    return this.customerRepository.save(customer);

  }
  
  @Override
  public Customer getCustomerById(UUID customerId){
    log.info("In get Customer in ServiceImpl");
    
   Optional<Customer> customer = this.customerRepository.findById(customerId);
   
   if (!customer.isPresent()) {
      log.warn("Customer with ID {} not found", customerId);
      
    }
    
    log.info("Customer found: {}", customer.get());
    return customer.get();   
      
  }

}
