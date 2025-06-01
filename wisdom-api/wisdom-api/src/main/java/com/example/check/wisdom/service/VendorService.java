package com.example.check.wisdom.service;

import java.util.List;
import java.util.UUID;


//import com.example.check.wisdom.data.entity.Customer;
import com.example.check.wisdom.data.entity.Vendor;

public interface VendorService {
  public List<Vendor> findVendorByEmail(String email);
  public Iterable<Vendor> getAllVendors(); // Method to retrieve all Vendors
  public Vendor saveVendor(Vendor vendor); // Method to save a Customer
  public Vendor getVendorById(UUID vendorId); // Method to find a Customer by ID
}
