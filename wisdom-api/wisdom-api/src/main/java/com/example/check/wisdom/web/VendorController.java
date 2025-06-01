// border wisdom brown
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
import org.springframework.web.bind.annotation.RestController;

import com.example.check.wisdom.data.entity.Vendor;
import com.example.check.wisdom.service.VendorService;
import com.example.check.wisdom.util.exception.BadRequestException;

import lombok.extern.slf4j.Slf4j;


@CrossOrigin(origins = "*") // Allow cross-origin requests from any origin
@RequestMapping ("/vendors") // Base path for all vendor related endpoints
@RestController
@Slf4j
public class VendorController {
  private final VendorService vendorService;

  public VendorController(VendorService vendorService) {
    super();
    this.vendorService = vendorService;

    log.info("VendorController initialized with vendorService");
  }

  @GetMapping ("/healthCheck")
  public void healthCheck() {
    log.info("VendorController is up and running");
  }
  
  @GetMapping ("/vendorEmail")
  public List<Vendor> findVendorByEmail(@RequestParam(required=false) String vendorEmail) {
    log.info("Email from path:", vendorEmail);
    List<Vendor> vendors = new ArrayList<>();
    try{
      if (StringUtils.hasLength(vendorEmail)) {
        vendors = this.vendorService.findVendorByEmail(vendorEmail);
        //return customers;
      } else {
        log.warn("Email id not found in Vendor Controller sending all Customers selected by me through {}");
        //getAllCustomers(); 
      }
  } catch (Exception e) {
      log.error("Error occurred while finding vendors by email in : {}", e.getMessage());
    }
    return vendors;
  }

  @GetMapping("/vendors")
  public Iterable<Vendor> getAllVendors() {
    log.info("In getAll vendors in VendorController");  
    try {
      return this.vendorService.getAllVendors();
    } catch (Exception e) {
      log.error("Error occurred while retrieving all vendors: {}", e.getMessage());
      return this.vendorService.getAllVendors(); // or handle the error appropriately
    }
    //return this.vendorService.getAllVendors();
  }

//get Vendor by Id
@GetMapping("/vendors/{vendorId}")
public Vendor getVendor(@PathVariable UUID vendorId){
  
  Vendor vendor = this.vendorService.getVendorById(vendorId);
  if (vendor == null) {
    log.warn("Vendor with ID {} not found", vendorId);
    throw new com.example.check.wisdom.util.exception.NotFoundException("Vendor not found with ID: " + vendorId);
  }
  return vendor; 
}

  //save Customer Product
  @PutMapping("/vendor/{vendorId}")
  public Vendor saveVendor(@PathVariable UUID vendorId, @RequestBody Vendor vendor) {
      if(!vendorId.equals(vendor.getVendorId())){
        throw new BadRequestException("vendorId on path must match body");
      }    
      return this.vendorService.saveVendor(vendor);      
  }
  
  

}
