package com.dataproject.yorha.service;

import com.dataproject.yorha.entity.WeaponType;
import com.dataproject.yorha.repository.WeaponTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeaponTypeService {

    @Autowired
    private WeaponTypeRepository weaponTypeRepository;

    public List<WeaponType> findAll(){ return weaponTypeRepository.findAll(); }

    public Optional<WeaponType> findById(String id){ return weaponTypeRepository.findById(id); }
}
