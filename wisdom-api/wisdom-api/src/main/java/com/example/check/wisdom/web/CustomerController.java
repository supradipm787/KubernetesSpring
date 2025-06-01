// border wisdom brown
package com.example.check.wisdom.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.check.wisdom.data.entity.Customer;
//import com.example.check.wisdom.data.repository.CustomerRepository;
import com.example.check.wisdom.service.CustomerService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.check.wisdom.util.exception.BadRequestException;

@CrossOrigin(origins = "*")
@RequestMapping ("/customers") // Base path for all customer related endpoints
@RestController
@Slf4j
public class CustomerController {
 
  
  private final CustomerService customerService;
  
  public CustomerController(CustomerService customerService) {
    super();
    this.customerService = customerService;

    log.info("CustomerController initialized with customerService");
  }

  @GetMapping ("/customer")
  public List<Customer> findCustomerByEmail(@RequestParam(required=false) String email) {
    log.info("Email from path:", email);
    List<Customer> customers = new ArrayList<>();
    try{
    if (StringUtils.hasLength(email)) {
      customers = this.customerService.findCustomerByEmail(email);
      //return customers;
    } else {
      log.warn("Email id not found in Customer Controller sending all Customers selected by me through {}");
      //getAllCustomers(); 
    }
  } catch (Exception e) {
      log.error("Error occurred while finding customer by email in : {}", e.getMessage());
    }
    return customers;
  }//end of findCustomerByEmail
  
  @GetMapping ("/customers")
  public Iterable<Customer> getAllCustomers() {
    log.info("In all get customers");  
    
    try { 
      return this.customerService.getAllCustomers();
    } catch (Exception e) {
      log.error("Error occurred while retrieving all customers: {}", e.getMessage());
      return this.customerService.getAllCustomers(); // or handle the error appropriately
    }
  
  }//end of getAllCustomers from Repo

//get Customer by Id
@GetMapping("/customers/{customerId}")
public Customer getCustomer(@PathVariable UUID customerId){
  
  Customer customer = this.customerService.getCustomerById(customerId);
  if (customer == null) {
    log.warn("Customer with ID {} not found", customerId);
    throw new com.example.check.wisdom.util.exception.NotFoundException("Customer not found with ID: " + customerId);
  }
  return customer; 
 }

  //save Customer
  @PutMapping("/customer/{customerId}")
  public com.example.check.wisdom.data.entity.Customer saveCustomer(@PathVariable UUID customerId, @RequestBody Customer customer) {
      if(!customerId.equals(customer.getCustomerId())){
        throw new BadRequestException("customerId on path must match body");
      }    
      return this.customerService.saveCustomer(customer);
      
  }

}//end of Customer controller