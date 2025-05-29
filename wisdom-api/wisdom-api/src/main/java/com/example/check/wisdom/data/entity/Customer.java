package com.example.check.wisdom.data.entity;

import java.util.UUID;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Data
@Table(name = "customers")
@Entity
public class Customer {  
   
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID) 
    private UUID customerId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    
    private String phone;
    private String address;

    // Additional fields and methods can be added as needed
}
