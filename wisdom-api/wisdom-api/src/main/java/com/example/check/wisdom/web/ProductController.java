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


import com.example.check.wisdom.data.entity.Product;

import com.example.check.wisdom.service.ProductService;
import com.example.check.wisdom.util.exception.BadRequestException;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*") 
@RequestMapping ("/products") // Base path for all product related endpoints
@RestController
@Slf4j
public class ProductController {

  private final ProductService productService;
  
  public ProductController(ProductService productService) {
    super();
    this.productService = productService;

    log.info("ProductController initialized with productService");
  }

  @GetMapping ("/productName")
  public List<Product> findProductByName(@RequestParam(required=false) String productName) {
    
    List<Product> products = new ArrayList<>();
    try {
      log.info("Product name from path:", productName);
      
      if (StringUtils.hasLength(productName)) {
        products = this.productService.findProductByName(productName);
        
      } else {
        log.warn("Product Name not found sending all Products data selected by me through /products");
        
      }
  } catch (Exception e) {
      log.error("Error occurred while finding product by productName: {}", e.getMessage());
    }
    return products;
  }//end of findCustomerByEmail
  
  
  
  @GetMapping ("/products")
  public Iterable<Product> getAllProducts() {
    log.info("In all get products");    
    try{
      return this.productService.getAllProducts();
    } catch (Exception e) { 
      log.error("Error occurred while retrieving all products: {}", e.getMessage());
      return this.productService.getAllProducts(); // or handle the error appropriately
    }
    
  }//end of getAllProducts from Repo

//get Product by Id
@GetMapping("/products/{productId}")
public Product getProduct(@PathVariable UUID productId){
  
  Product product = this.productService.getProductById(productId);
  if (product == null) {
    log.warn("Product with ID {} not found", productId);
    throw new com.example.check.wisdom.util.exception.NotFoundException("Product not found with ID: " + productId);
  }
  return product; 
}

  //save Customer Product
  @PutMapping("/product/{productId}")
  public com.example.check.wisdom.data.entity.Product saveProduct(@PathVariable UUID productId, @RequestBody Product product) {
      if(!productId.equals(product.getProductId())){
        throw new BadRequestException("productId on path must match body");
      }    
      return this.productService.saveProduct(product);      
  }

}
