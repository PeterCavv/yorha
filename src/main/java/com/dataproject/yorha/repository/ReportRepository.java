package com.dataproject.yorha.repository;

import com.dataproject.yorha.model.Report;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends MongoRepository<Report, String> {

//    @Query("{}")
//    Page<Report> findAllReports(Pageable pageable);
}
