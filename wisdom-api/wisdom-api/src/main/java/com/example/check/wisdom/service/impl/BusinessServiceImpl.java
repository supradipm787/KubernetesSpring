//no green wisdom black 
package com.example.check.wisdom.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;


//import com.example.check.wisdom.data.entity.Service;
import com.example.check.wisdom.data.repository.ServiceRepository;
import com.example.check.wisdom.service.BusinessService;

import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;

@Slf4j
@org.springframework.stereotype.Service
public class BusinessServiceImpl implements BusinessService {

    // Assuming you have a repository for Service entity
    @Autowired
    private ServiceRepository serviceRepository;    

    @Override
    public List<com.example.check.wisdom.data.entity.Service> findServiceByName(String name) {
    List <com.example.check.wisdom.data.entity.Service> services = new ArrayList<com.example.check.wisdom.data.entity.Service>();
    log.info("In find By Name in BusinessServiceImpl: {}", name);
    if (StringUtils.hasLength(name)) {
      Optional<com.example.check.wisdom.data.entity.Service> service = serviceRepository.findByName(name);
      if (service.isPresent()) {
        services.add(service.get());
      } 
      return services;
    } 

    return services;
    }

    @Override
    public Iterable<com.example.check.wisdom.data.entity.Service> getAllServices() {
        return serviceRepository.findAll();
    }
    // Additional methods that can be implemented at my wish
  
  @Override
  public com.example.check.wisdom.data.entity.Service saveService(com.example.check.wisdom.data.entity.Service service) {
    log.info("In save Service in ServiceImpl");
    
    return this.serviceRepository.save(service);

  }
  
  @Override
  public com.example.check.wisdom.data.entity.Service getServiceById(UUID serviceId){
    log.info("In get Service in Business ServiceImpl");
    
   Optional<com.example.check.wisdom.data.entity.Service> service = this.serviceRepository.findById(serviceId);
   
   if (!service.isPresent()) {
      log.warn("Service with ID {} not found", serviceId);
      
    }
    
    log.info("Product found: {}", service.get()); 
    return service.get();   
      
  }

}
