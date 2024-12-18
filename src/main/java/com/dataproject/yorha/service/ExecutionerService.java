package com.dataproject.yorha.service;

import com.dataproject.yorha.entity.Executioner;
import com.dataproject.yorha.entity.History;
import com.dataproject.yorha.repository.ArmoryRepository;
import com.dataproject.yorha.repository.ExecutionerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExecutionerService {

    @Autowired
    public ExecutionerRepository executionerRepository;

    @Autowired
    public ArmoryRepository armoryRepository;

    public List<Executioner> findAll(){ return executionerRepository.findAll(); }

    public Optional<Executioner> findById(String id){ return executionerRepository.findById(id); }

    public void createExecutioner( Executioner executioner ){

        List<History> historyList = new ArrayList<>();
        executioner.setHistory(historyList);
        executioner.setEquipment(armoryRepository.findAll().stream()
                .filter(weapon -> weapon.getName()
                        .equals("YoRHa-issue Blade"))
                .toList().get(0));

        executionerRepository.save(executioner);
    }

}
