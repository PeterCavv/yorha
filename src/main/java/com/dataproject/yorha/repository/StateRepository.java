package com.dataproject.yorha.repository;

import com.dataproject.yorha.model.State;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends MongoRepository<State, ObjectId> {
}
