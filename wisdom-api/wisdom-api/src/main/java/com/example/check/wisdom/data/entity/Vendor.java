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
@Table(name = "vendors")
@Entity
public class Vendor {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID) 
    private UUID vendorId;
    
    private String name;
    private String contact;
    
    @Column(unique = true)
    private String email;
    
    private String phone;
    
    private String address;

    // Additional fields and methods can be added as needed



}
