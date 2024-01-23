package com.dataproject.yorha.service;

import com.dataproject.yorha.DTO.TypeDTO;
import com.dataproject.yorha.entity.Type;
import com.dataproject.yorha.repository.TypeRepository;
import org.bson.types.ObjectId;
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

    public Optional<Type> oneType(ObjectId id) {
        return typeRepository.findById(id);
    }

    public Type save(TypeDTO typeDTO){
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
}
