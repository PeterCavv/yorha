package com.dataproject.yorha.repository;

import com.dataproject.yorha.model.Android;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import com.dataproject.yorha.model.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AndroidRepository extends MongoRepository<Android, String> {

    long countByType(Type type);
//    @Query("{}")
//    Page<Android> findAllAndroids(Pageable pageable);
}
