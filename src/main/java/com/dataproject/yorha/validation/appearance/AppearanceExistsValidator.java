package com.dataproject.yorha.validation.appearance;

import com.dataproject.yorha.repository.AppearanceRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppearanceExistsValidator implements ConstraintValidator<AppearanceExists, String> {

    @Autowired
    private AppearanceRepository appearanceRepository;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context){
        if( id == null || id.isEmpty() ){
            return false;
        }

        return appearanceRepository.existsById(id);
    }
}
