package com.extensivepay.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.extensivepay.model.ExtensivePayModel;

@Repository
public interface IExtensivePayRepository extends ReactiveMongoRepository<ExtensivePayModel, String>{

}
