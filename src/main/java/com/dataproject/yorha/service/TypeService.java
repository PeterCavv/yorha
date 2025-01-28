package com.dataproject.yorha.service;

import com.dataproject.yorha.DTO.TypeDTO;
import com.dataproject.yorha.entity.Type;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public List<Type> allTypes() {
        return typeRepository.findAll();
    }

    public Optional<Type> oneType(String id) {
        return typeRepository.findById(id);
    }

    public Type createType(TypeDTO typeDTO){
        Type type = new Type();
        type.setName(typeDTO.getName());
        type.setDesc(typeDTO.getDesc());
        if(!typeDTO.getResume().isEmpty() && !typeDTO.getResume().isBlank()){
            type.setResume(typeDTO.getResume());
        } else {
            type.setResume("");
        }

        return typeRepository.save(type);
    }

    public void delete(String name){
        typeRepository.deleteByName(name);
    }

    public void validateType(String typeId) {
        if( !typeRepository.existsById( typeId ) ){
            throw new ObjectNotFoundException(
                    "Type not found with the ID: " + typeId );
        }
    }
}
