package com.dataproject.yorha.service;

import com.dataproject.yorha.entity.Executioner;
import com.dataproject.yorha.repository.ExecutionerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExecutionerService {

    @Autowired
    public ExecutionerRepository executionerRepository;

    public List<Executioner> findAll(){ return executionerRepository.findAll(); }

    public Optional<Executioner> findById(String id){ return executionerRepository.findById(id); }

    public void createExecutioner( Executioner executioner ){
        executionerRepository.save(executioner);
    }

}
