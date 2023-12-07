package com.dataproject.yorha.service;

import com.dataproject.yorha.model.Type;
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

    public Type save(String name, String resume, String desc){
        return typeRepository.insert(new Type(name, resume, desc));
    }

    public void delete(String name){
        typeRepository.deleteByName(name);
    }
}
