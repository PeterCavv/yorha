package com.dataproject.yorha.repository;

import com.dataproject.yorha.entity.Armory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmoryRepository extends MongoRepository<Armory, String> {
}
