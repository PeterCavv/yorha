package com.dataproject.yorha.repository;

import com.dataproject.yorha.entity.Executioner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExecutionerRepository extends MongoRepository<Executioner, String> {
}