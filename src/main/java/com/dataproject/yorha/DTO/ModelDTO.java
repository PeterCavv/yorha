package com.dataproject.yorha.DTO;

import com.dataproject.yorha.entity.Model;
import com.dataproject.yorha.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ModelDTO {

    @Autowired
    private ModelRepository modelRepository;

    public Model stringToModel(String modelName){
        List<Model> modelList = modelRepository.findAll();
        for ( Model model : modelList) {
            if(model.getName().equals(modelName)){
                return model;
            }
        }

        return null;
    }

}
