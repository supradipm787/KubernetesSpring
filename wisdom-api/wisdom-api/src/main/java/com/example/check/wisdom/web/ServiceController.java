package com.example.check.wisdom.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.check.wisdom.service.BusinessService;
import com.example.check.wisdom.util.exception.BadRequestException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping ("/services") // Base path for all service related endpoints
@Slf4j
@CrossOrigin(origins = "*") 
@RestController
public class ServiceController {

  private final BusinessService service;

  public ServiceController (BusinessService service) {
    super();
    this.service = service;

    log.info("Myself in service of Almighty");
  }
  
  @GetMapping ("/service")
  public List<com.example.check.wisdom.data.entity.Service> findserviceByname(@RequestParam(required=false) String service_name) {
    log.info("service from path:", service_name);
    List<com.example.check.wisdom.data.entity.Service> servicename = new ArrayList<>();
    try{
      
      if (StringUtils.hasLength(service_name)) {        
        servicename = this.service.findServiceByName(service_name);
        
      } else {
        log.warn("service not found in {}");
        //getAllCustomers(); 
      }
  } catch (Exception e) {
      log.error("Error occurred while finding service in : {}", e.getMessage());
    }
    return servicename;
  }



  @GetMapping ("/services")
  public Iterable<com.example.check.wisdom.data.entity.Service> getAllServices() {
    log.info("In getAll services in ServiceController");  
    try {
      return this.service.getAllServices();
    } catch (Exception e) {
      log.error("Error occurred while retrieving all vendors: {}", e.getMessage());
      return this.service.getAllServices(); // or handle the error appropriately
    }
    //return this.vendorService.getAllVendors();
  
  }

//get Customer Service by Id
@GetMapping("/{serviceId}")
public com.example.check.wisdom.data.entity.Service getService(@PathVariable UUID serviceId){
  
  com.example.check.wisdom.data.entity.Service service = this.service.getServiceById(serviceId);
  if (service == null) {
    log.warn("Service with ID {} not found", serviceId);
    throw new com.example.check.wisdom.util.exception.NotFoundException("Service not found with ID: " + serviceId);
  }
  return service; 
 }

  //save Customer
  @PutMapping("/{serviceId}")
  public com.example.check.wisdom.data.entity.Service saveBusinessService(@PathVariable UUID serviceId, @RequestBody com.example.check.wisdom.data.entity.Service service) {
      if(!serviceId.equals(service.getServiceId())){
        throw new BadRequestException("serviceId on path must match body");
      }    
      return this.service.saveService(service);
      
  }


}
