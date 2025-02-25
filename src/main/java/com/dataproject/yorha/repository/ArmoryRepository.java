package com.dataproject.yorha.repository;

import com.dataproject.yorha.model.Armory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmoryRepository extends MongoRepository<Armory, String> {
}
