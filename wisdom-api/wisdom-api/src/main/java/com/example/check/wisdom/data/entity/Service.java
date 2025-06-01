package com.example.check.wisdom.data.entity;

import java.util.UUID;
import lombok.Data;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Table(name="services")
@Entity
public class Service {
  @Id 
  @GeneratedValue(strategy = GenerationType.UUID) 
  private UUID serviceId;
  @Column(unique = true)
  private String name;
  private BigDecimal price;

}
