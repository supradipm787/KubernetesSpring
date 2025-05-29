package com.example.check.wisdom.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.check.wisdom.data.entity.Customer;
//import com.example.check.wisdom.data.repository.CustomerRepository;
import com.example.check.wisdom.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerController {
  //private final CustomerRepository customerRepository;
  //@Autowired
  
  private final CustomerService customerService;
  
  public CustomerController(CustomerService customerService) {
    super();
    this.customerService = customerService;

    log.info("CustomerController initialized with CustomerRepository");
  }

  @GetMapping ("/customer")
  public List<Customer> findCustomerByEmail(@RequestParam(required=false) String email) {
    log.info("Email from path:", email);
    List<Customer> customers = new ArrayList<>();
    if (StringUtils.hasLength(email)) {
      customers = this.customerService.findCustomerByEmail(email);
      //return customers;
    } else {
      log.warn("Email id not found sending all Customers data selected by me");
      //getAllCustomers(); 
    }
    return customers;
  }//end of findCustomerByEmail
  
  @GetMapping ("/customers")
  public Iterable<Customer> getAllCustomers() {
    log.info("In all get customers");  
    
    return this.customerService.getAllCustomers();
  }//end of getAllCustomers from Repo

}//end of Customer