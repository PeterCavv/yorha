package com.dataproject.yorha.repository;

import com.dataproject.yorha.model.Operator;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperatorRepository extends MongoRepository<Operator, ObjectId> {

}
