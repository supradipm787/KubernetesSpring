//no green wisdom brown 
package com.example.check.wisdom.service;

import java.util.List;
import java.util.UUID;

import com.example.check.wisdom.data.entity.Product;
import com.example.check.wisdom.data.entity.Service;

public interface BusinessService {
  public List<Service> findServiceByName(String name);
  public Iterable<Service> getAllServices(); // Method to retrieve all Products
  public Service saveService(Service service); // Method to save a Product
  public Service getServiceById(UUID serviceId); // Method to find a Product by ID
}
