package com.dataproject.yorha.repository;

import com.dataproject.yorha.entity.Android;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AndroidRepository extends MongoRepository<Android, ObjectId> {

}
