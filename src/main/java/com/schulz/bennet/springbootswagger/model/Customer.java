package com.schulz.bennet.springbootswagger.model;

import java.io.Serializable;


public class Customer implements Serializable {

  private String customerId;
  private String name;
  private String city;
  private String status;
  
  public Customer() {
  }
  
  public Customer(String customerId, String name, String city, String status) {
    this.name = name;
    this.customerId = customerId;
    this.city = city;
    this.status = status;
  }
  
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getCustomerId() {
    return customerId;
  }
  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  
  @Override
  public String toString() {
    return super.toString();
  }
    
}


