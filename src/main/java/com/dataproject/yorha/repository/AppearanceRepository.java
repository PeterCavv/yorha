package com.dataproject.yorha.repository;

import com.dataproject.yorha.model.Appearance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppearanceRepository extends MongoRepository<Appearance, String> {
}
