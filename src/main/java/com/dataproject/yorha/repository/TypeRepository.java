package com.dataproject.yorha.repository;

import com.dataproject.yorha.model.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends MongoRepository<Type, String> {
    void deleteByName(String name);
}
