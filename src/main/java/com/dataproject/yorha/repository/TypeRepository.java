package com.dataproject.yorha.repository;

import com.dataproject.yorha.model.Type;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends MongoRepository<Type, ObjectId> {
    void deleteByName(String name);
}
