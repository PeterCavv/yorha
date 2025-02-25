package com.dataproject.yorha.service;

import com.dataproject.yorha.model.Model;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public List<Model> getAllModels(){ return modelRepository.findAll();}

    //METHODS

    public void validateModel( String idModel ){
        if( !modelRepository.existsById( idModel ) ){
            throw new ObjectNotFoundException(
                    "Model not found with the ID: " + idModel );
        }
    }

}
