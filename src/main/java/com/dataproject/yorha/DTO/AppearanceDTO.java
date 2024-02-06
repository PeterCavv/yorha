package com.dataproject.yorha.DTO;

import com.dataproject.yorha.entity.Appearance;
import com.dataproject.yorha.repository.AppearanceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AppearanceDTO {

    @Autowired
    private AppearanceRepository appearanceRepository;

    public Appearance stringToAppearance(String appearanceName){
        List<Appearance> appearanceList = appearanceRepository.findAll();
        for ( Appearance appearance : appearanceList ){
            if(appearance.getName().equals(appearanceName)){
                return appearance;
            }
        }

        return null;
    }
}
