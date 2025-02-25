package com.dataproject.yorha.validation.model;

import com.dataproject.yorha.repository.ModelRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ModelExistsValidator implements ConstraintValidator<ModelExists, String> {

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context){
        if( id == null || id.isEmpty() ){
            return false;
        }

        return modelRepository.existsById(id);
    }
}
