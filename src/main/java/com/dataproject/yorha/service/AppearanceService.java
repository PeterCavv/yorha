package com.dataproject.yorha.service;

import com.dataproject.yorha.entity.Appearance;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.repository.AppearanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppearanceService {
    @Autowired
    private AppearanceRepository appearanceRepository;

    public List<Appearance> getAllAppearances(){ return appearanceRepository.findAll(); }

    //METHODS

    public void validateAppearance(String idAppearance){
        if( !appearanceRepository.existsById( idAppearance ) ){
            throw new ObjectNotFoundException(
                    "Appearance not found with the ID: " + idAppearance );
        }
    }
}
