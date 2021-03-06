package com.extensivepay.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class CreditCardModel {

	private String id;
	private String creditCardNumber;
	private String creditCardAccount;
	private String type;
	private Double creditLimit;
	private Double avalibleAmount;
	private String createdAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getCreditCardAccount() {
		return creditCardAccount;
	}

	public void setCreditCardAccount(String creditCardAccount) {
		this.creditCardAccount = creditCardAccount;
	}

	public Double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}
	
	public Double getAvalibleAmount() {
		return avalibleAmount;
	}

	public void setAvalibleAmount(Double avalibleAmount) {
		this.avalibleAmount = avalibleAmount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
