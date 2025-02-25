package com.dataproject.yorha.repository;

import com.dataproject.yorha.model.History;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryRepository extends MongoRepository<History, String>{

}
