package com.dataproject.yorha.service;

import com.dataproject.yorha.model.Armory;
import com.dataproject.yorha.repository.ArmoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArmoryService {

    @Autowired
    private ArmoryRepository armoryRepository;

    public List<Armory> findAll(){ return armoryRepository.findAll(); }

    public Optional<Armory> findById(String id){ return armoryRepository.findById(id); }
}
