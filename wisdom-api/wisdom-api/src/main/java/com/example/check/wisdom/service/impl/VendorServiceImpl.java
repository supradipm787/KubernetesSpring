package com.example.check.wisdom.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.check.wisdom.data.entity.Product;
import com.example.check.wisdom.data.entity.Vendor;
import com.example.check.wisdom.service.VendorService;



import com.example.check.wisdom.data.repository.VendorRepository;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
public class VendorServiceImpl implements VendorService{
  // Implement the methods defined in VendorService interface here

  @Autowired
  private VendorRepository vendorRepository;

  @Override
  public List<Vendor> findVendorByEmail(String email) {
     List <Vendor> vendors = new ArrayList<Vendor>();
    log.info("In find Vendor By Email in VendorServiceImpl: {}", email);
    if (StringUtils.hasLength(email)) {
      Optional<Vendor> vendor = vendorRepository.findByEmail(email);
      if (vendor.isPresent()) {
        vendors.add(vendor.get());
      } 
      return vendors;
    } 

    return vendors;
  }

  @Override
  public Iterable<Vendor> getAllVendors() {
    // Logic to retrieve all vendors
    log.info("In all get Vendors in VendorServiceImpl");
    return this.vendorRepository.findAll();
  }

  @Override
  public com.example.check.wisdom.data.entity.Vendor saveVendor(Vendor vendor) {
    log.info("In save Vendor in ServiceImpl");
    
    return this.vendorRepository.save(vendor);

  }
  
  @Override
  public Vendor getVendorById(UUID vendorId){
    log.info("In get Vendor in ServiceImpl");
    
   Optional<Vendor> vendor = this.vendorRepository.findById(vendorId);
   
   if (!vendor.isPresent()) {
      log.warn("Vendor with ID {} not found", vendorId);
      
    }
    
    log.info("Vendor found: {}", vendor.get());
    return vendor.get();   
      
  }


}
