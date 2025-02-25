package com.dataproject.yorha.validation.android;

import com.dataproject.yorha.repository.AndroidRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AndroidExistsValidator implements ConstraintValidator<AndroidExists, String>{

    @Autowired
    private AndroidRepository androidRepository;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context){
        if (id == null || id.isEmpty()){
            return false;
        }

        return androidRepository.existsById(id);
    }
}
