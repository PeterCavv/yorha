package com.dataproject.yorha.repository;

import com.dataproject.yorha.model.Report;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends MongoRepository<Report, ObjectId> {
}
