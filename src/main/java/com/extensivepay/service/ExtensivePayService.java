package com.extensivepay.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.extensivepay.model.CreditCardMainModel;
import com.extensivepay.model.CreditCardModel;
import com.extensivepay.model.ExtensivePayModel;
import com.extensivepay.model.FixedTermVipModel;
import com.extensivepay.model.MainModel;
import com.extensivepay.repository.IExtensivePayRepository;

import reactor.core.publisher.Mono;

@Service
public class ExtensivePayService implements IExtensivePayService {
	
	@Autowired
	@Qualifier("bankAccountMain")
	WebClient client;
	
	@Autowired
	@Qualifier("bankFixedTermAccountVip")
	WebClient client2;
	
	@Autowired
	@Qualifier("creditCard")
	WebClient client3;
		
	@Autowired
	IExtensivePayRepository iExtensivePayRepository;
	
	MainModel mainModel = new MainModel();
	
	CreditCardMainModel creditCardMainModel = new CreditCardMainModel();
	
	FixedTermVipModel fixedTermVipModel = new FixedTermVipModel();
	
	CreditCardModel creditCardModel = new CreditCardModel();
			
	  /* Microservice that connects */
	  public Mono<MainModel> findAccountNumber(String accountNumber) {
		  Map<String, Object> param = new HashMap<String, Object>();
	    param.put("accountNumber", accountNumber);
	    return client.get()
	.uri("/find-by/{accountNumber}",param)
	.accept(MediaType.APPLICATION_JSON_UTF8)
	.retrieve()
	.bodyToMono(MainModel.class)
	.switchIfEmpty(Mono.empty());
	  }
	  
	  /* Microservice that connects */
	  public Mono<CreditCardMainModel> findCreditCardNumber (String creditCardNumber) {
	    Map<String, Object> param = new HashMap<String, Object>();
	    param.put("creditCardNumber", creditCardNumber);
	    return client.get()
	.uri("/find-credit-by/{creditCardNumber}",param)
	.accept(MediaType.APPLICATION_JSON_UTF8)
	.retrieve()
	.bodyToMono(CreditCardMainModel.class)
	.switchIfEmpty(Mono.empty());
	  }
	  
	  /* Microservice that connects */
	  public Mono<MainModel> updateAccountNumber(MainModel mainModel) {
	    return client.put()
	.uri("/update-amount")
	.accept(MediaType.APPLICATION_JSON_UTF8)
	.contentType(MediaType.APPLICATION_JSON_UTF8)
	.syncBody(mainModel)
	.retrieve()
	.bodyToMono(MainModel.class)
	.switchIfEmpty(Mono.empty());
	  }
	  
	  public Mono<CreditCardMainModel> updateCreditCardNumber(CreditCardMainModel creditCardModel) {
		    return client.put()
		.uri("/update-credit-amount")
		.accept(MediaType.APPLICATION_JSON_UTF8)
		.contentType(MediaType.APPLICATION_JSON_UTF8)
		.syncBody(creditCardModel)
		.retrieve()
		.bodyToMono(CreditCardMainModel.class)
		.switchIfEmpty(Mono.empty());
		  }
	  
	  public Mono<FixedTermVipModel> updateAmountFtv(FixedTermVipModel fixedTermVipModel) {
		    return client2.put()
		        .uri("/update")
		        .accept(MediaType.APPLICATION_JSON_UTF8)
		        .contentType(MediaType.APPLICATION_JSON_UTF8)
		        .syncBody(fixedTermVipModel)
		        .retrieve()
		        .bodyToMono(FixedTermVipModel.class)
		        .switchIfEmpty(Mono.empty());
		  }
	  
	  public Mono<CreditCardModel> updateAmountCc(CreditCardModel creditCardModel) {
		    return client3.put()
		        .uri("/update")
		        .accept(MediaType.APPLICATION_JSON_UTF8)
		        .contentType(MediaType.APPLICATION_JSON_UTF8)
		        .syncBody(creditCardModel)
		        .retrieve()
		        .bodyToMono(CreditCardModel.class)
		        .switchIfEmpty(Mono.empty());
		  }
	  
	  
	  	  
	  @Override
	  public Mono<ExtensivePayModel> insertExtensivePay(ExtensivePayModel extensivePayModel){
		  
		  return findAccountNumber(extensivePayModel.getAccountNumber())
				  .flatMap(x -> {
					  extensivePayModel.setAcBeforeAmount(x.getCurrentAmount());
					  
					  return findCreditCardNumber(extensivePayModel.getCreditCardNumber())
							  .flatMap(y -> {
								  extensivePayModel.setCcBeforeAmount(y.getAvalibleAmount());
								  
								  extensivePayModel.setAcAfterAmount(extensivePayModel.getAcBeforeAmount() - extensivePayModel.getAmount());
								  extensivePayModel.setCcAfterAmount(extensivePayModel.getCcBeforeAmount() + extensivePayModel.getAmount());
								  
								  mainModel.setAccountNumber(extensivePayModel.getAccountNumber());
								  mainModel.setCurrentAmount(extensivePayModel.getAcAfterAmount());
								  
								  creditCardMainModel.setCreditCardNumber(extensivePayModel.getCreditCardNumber());
								  creditCardMainModel.setAvalibleAmount(extensivePayModel.getCcAfterAmount());
								  
								  fixedTermVipModel.setAccountNumber(mainModel.getAccountNumber());
								  fixedTermVipModel.setCurrentAmount(mainModel.getCurrentAmount());
								  
								  creditCardModel.setCreditCardNumber(creditCardMainModel.getCreditCardNumber());
								  creditCardModel.setAvalibleAmount(creditCardMainModel.getAvalibleAmount());
								  
								  return updateAccountNumber(mainModel).flatMap(z -> {
									  
									  return updateCreditCardNumber(creditCardMainModel).flatMap(a -> {
										  										  
										  return updateAmountFtv(fixedTermVipModel).flatMap(b -> {
											  
											  return updateAmountCc(creditCardModel).flatMap(c -> {
												  
												  return iExtensivePayRepository.save(extensivePayModel);
											  });
										  });
										  
									  });
									  
								  });
								    
								  
							  });
				  });
		  
		  
	  }
	  
}
