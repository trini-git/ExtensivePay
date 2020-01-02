package com.extensivepay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensivepay.model.CreditCardMainModel;
import com.extensivepay.model.ExtensivePayModel;
import com.extensivepay.model.MainModel;
import com.extensivepay.service.ExtensivePayService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/extensive-pay")
public class ExtensivePayController {
	
	@Autowired
	ExtensivePayService extensivePayService;
	
	MainModel mainModel = new MainModel();
	
	CreditCardMainModel creditCardMainModel = new CreditCardMainModel();

	/*to update the amount depends of Operation Type*/
	@PostMapping("/insert")
	public Mono<ExtensivePayModel> insertExtensivePay(@RequestBody ExtensivePayModel extensivePayModel) {
		
		return extensivePayService.insertExtensivePay(extensivePayModel);
		
	}
	
	@GetMapping("/find-by/{accountNumber}")
	public Mono<MainModel> findAccountNumber(@PathVariable String accountNumber) {
		
		return extensivePayService.findAccountNumber(accountNumber);
		
	}
	
	@GetMapping("/find-credit-by/{creditCardNumber}")
	public Mono<CreditCardMainModel> findCreditCardNumber(@PathVariable String creditCardNumber) {
		
		return extensivePayService.findCreditCardNumber(creditCardNumber);
		
	}
	
	
}
