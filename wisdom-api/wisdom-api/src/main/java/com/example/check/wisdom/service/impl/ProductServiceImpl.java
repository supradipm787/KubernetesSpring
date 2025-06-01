package com.example.check.wisdom.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import com.example.check.wisdom.data.entity.Product;

import com.example.check.wisdom.data.repository.ProductRepository;
import com.example.check.wisdom.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{
  @Autowired 
  private ProductRepository productRepository;
  
  @Override
  public List<Product> findProductByName(String name) {
    List <Product> products = new ArrayList<Product>();
    log.info("In find Product By Name in ProductServiceImpl: {}", name);
    if (StringUtils.hasLength(name)) {
      Optional<Product> product = productRepository.findByName(name);
      if (product.isPresent()) {
        products.add(product.get());
      } 
      return products;
    } 

    return products;
  }

  @Override
  public Iterable<Product> getAllProducts() {
    
    log.info("In all get Products in ProductServiceImpl");
    return this.productRepository.findAll();
  }

  
  @Override
  public com.example.check.wisdom.data.entity.Product saveProduct(Product product) {
    log.info("In save Product in ServiceImpl");
    
    return this.productRepository.save(product);

  }
  
  @Override
  public Product getProductById(UUID productId){
    log.info("In get Product in ServiceImpl");
    
   Optional<Product> product = this.productRepository.findById(productId);
   
   if (!product.isPresent()) {
      log.warn("Product with ID {} not found", productId);
      
    }
    
    log.info("Product found: {}", product.get());
    return product.get();   
      
  }


}
