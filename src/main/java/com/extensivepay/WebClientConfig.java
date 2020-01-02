package com.extensivepay;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  @Qualifier("bankAccountMain")
  public WebClient bankAccountMainWebClient() {
    return WebClient.create("http://localhost:8020/api/bankAccountMain");
  }
  
  @Bean
  @Qualifier("bankFixedTermAccountVip")
  public WebClient bankFixedTermAccountVipWebClient() {
    return WebClient.create("http://localhost:8020/api/bankFixedTermAccountVip");
  }
  
  @Bean
  @Qualifier("creditCard")
  public WebClient creditCardVipWebClient() {
    return WebClient.create("http://localhost:8020/api/creditCard");
  }
    
}
