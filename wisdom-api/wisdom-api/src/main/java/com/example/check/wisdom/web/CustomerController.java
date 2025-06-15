// border wisdom brown
package com.example.check.wisdom.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.check.wisdom.data.entity.Customer;
import com.example.check.wisdom.service.CustomerService;
import com.example.check.wisdom.util.exception.BadRequestException;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;

// @CrossOrigin(origins = "*")
@RequestMapping ("customers") // Base path for all customer related endpoints
@RestController
@Slf4j
public class CustomerController {
 
  
  private final CustomerService customerService;
  private final Map<String, Timer> timerMap;

  private static final String GET_ALL_CUSTOMERS = "getAllCustomers";
  private static final String ADD_CUSTOMER = "addCustomer";
  private static final String GET_CUSTOMER = "getCustomer";
  private static final String UPDATE_CUSTOMER = "updateCustomer";
  
  public CustomerController(CustomerService customerService, MeterRegistry registry) {
    super();
    this.customerService = customerService;

    log.info("CustomerController initialized with customerService");
    timerMap = new java.util.HashMap<>();
    // Registering timers for different operations
    timerMap.put(GET_ALL_CUSTOMERS, registry.timer(GET_ALL_CUSTOMERS));
    timerMap.put(ADD_CUSTOMER, registry.timer(ADD_CUSTOMER));
    timerMap.put(GET_CUSTOMER, registry.timer(GET_CUSTOMER));   
    timerMap.put(UPDATE_CUSTOMER, registry.timer(UPDATE_CUSTOMER));
  
  }

  
  @GetMapping ("/customerEmail") 
  public List<Customer> getCustomerByEmail(@RequestParam(required=false) String email) {
    log.info("Email from path:", email);
    Timer.Sample timer = Timer.start();
    List<Customer> customers = new ArrayList<>();
    try{
    if (StringUtils.hasLength(email)) {
      customers = this.customerService.findCustomerByEmail(email);
      timerMap.get(GET_CUSTOMER).record (()-> timer.stop(timerMap.get(GET_CUSTOMER)));
      return customers;
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