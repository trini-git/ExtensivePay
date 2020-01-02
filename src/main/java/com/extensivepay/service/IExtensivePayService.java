package com.extensivepay.service;

import com.extensivepay.model.ExtensivePayModel;

import reactor.core.publisher.Mono;

public interface IExtensivePayService {

	Mono<ExtensivePayModel> insertExtensivePay(ExtensivePayModel extensivePayModel);

}
