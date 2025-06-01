package com.example.check.wisdom.data.entity;

import java.util.UUID;
import java.math.BigDecimal;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Data
@Table(name = "products")
@Entity
public class Product {
  @Id 
  @GeneratedValue(strategy = GenerationType.UUID) 
  private UUID productId;
  
  @Column(unique = true)
  private String name;
  private BigDecimal price;
  
  
  //private Integer vendorId; // foreign key : primary key of Vendor
    //@OneToOne
    //@JoinColumn(name = "vendor_id") // this creates the foreign key column
    private UUID vendorId;

}
