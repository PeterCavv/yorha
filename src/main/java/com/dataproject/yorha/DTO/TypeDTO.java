package com.dataproject.yorha.DTO;

import com.dataproject.yorha.model.Type;
import com.dataproject.yorha.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TypeDTO {

    @Autowired
    private TypeRepository typeRepository;

    public Type stringToType(String typeName){
        List<Type> typeList = typeRepository.findAll();
        for ( Type type : typeList ){
            if(type.getName().equals(typeName)){
                return type;
            }
        }

        return null;
    }
}
