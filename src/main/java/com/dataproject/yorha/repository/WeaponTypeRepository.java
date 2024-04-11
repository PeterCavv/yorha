package com.dataproject.yorha.repository;

import com.dataproject.yorha.entity.WeaponType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponTypeRepository extends MongoRepository<WeaponType, String> {
}