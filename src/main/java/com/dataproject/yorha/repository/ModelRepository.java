package com.dataproject.yorha.repository;

import com.dataproject.yorha.entity.Model;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends MongoRepository<Model, String>{
}
