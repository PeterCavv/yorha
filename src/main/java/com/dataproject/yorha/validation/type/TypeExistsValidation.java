package com.dataproject.yorha.validation.type;

import com.dataproject.yorha.repository.TypeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TypeExistsValidation implements ConstraintValidator<TypeExists, String> {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context){
        if( id == null || id.isEmpty()){
            return false;
        }

        return typeRepository.existsById(id);
    }
}