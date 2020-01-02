package com.extensivepay.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "extensive_pay")
public class ExtensivePayModel {
  
  @Id
  private String id;  
  private String accountNumber;
  private Double acBeforeAmount;
  private Double acAfterAmount;
  private String creditCardNumber;
  private Double ccBeforeAmount;
  private Double ccAfterAmount;
  private Double amount; 
  private String createdAt;
    
  public ExtensivePayModel() {
	  SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
	    this.createdAt = formatter.format(new Date());
  }

  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getAccountNumber() {
    return accountNumber;
  }
  
  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }
  
  public Double getAcBeforeAmount() {
    return acBeforeAmount;
  }
  
  public void setAcBeforeAmount(Double acBeforeAmount) {
    this.acBeforeAmount = acBeforeAmount;
  }
  
  public Double getAcAfterAmount() {
    return acAfterAmount;
  }
  
  public void setAcAfterAmount(Double acAfterAmount) {
    this.acAfterAmount = acAfterAmount;
  }
  
  public String getCreditCardNumber() {
    return creditCardNumber;
  }
  
  public void setCreditCardNumber(String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
  }
  
  public Double getCcBeforeAmount() {
    return ccBeforeAmount;
  }
  
  public void setCcBeforeAmount(Double ccBeforeAmount) {
    this.ccBeforeAmount = ccBeforeAmount;
  }
  
  public Double getCcAfterAmount() {
    return ccAfterAmount;
  }
  
  public void setCcAfterAmount(Double ccAfterAmount) {
    this.ccAfterAmount = ccAfterAmount;
  }
  
  public Double getAmount() {
    return amount;
  }
  
  public void setAmount(Double amount) {
    this.amount = amount;
  }
  
  public String getCreatedAt() {
    return createdAt;
  }
  
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
  
}
