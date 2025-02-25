package com.dataproject.yorha.repository;

import com.dataproject.yorha.model.Operator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends MongoRepository<Operator, String> {

}
