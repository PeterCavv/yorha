package com.dataproject.yorha.repository;

import com.dataproject.yorha.entity.Android;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AndroidRepository extends MongoRepository<Android, ObjectId> {

}
