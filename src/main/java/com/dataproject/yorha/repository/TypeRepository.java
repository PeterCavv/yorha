package com.dataproject.yorha.repository;

import com.dataproject.yorha.entity.Type;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends MongoRepository<Type, String> {
    void deleteByName(String name);
}
