package com.dataproject.yorha.service;

import com.dataproject.yorha.entity.Android;
import com.dataproject.yorha.entity.Model;
import com.dataproject.yorha.repository.ModelRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public List<Model> getAllModels(){ return modelRepository.findAll();}

}
