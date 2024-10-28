package com.dataproject.yorha.repository;

import com.dataproject.yorha.entity.History;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryRepository extends MongoRepository<History, String>{

}
