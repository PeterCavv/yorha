package com.dataproject.yorha.repository;

import com.dataproject.yorha.model.Model;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends MongoRepository<Model, String>{
}
