package com.dataproject.yorha.service;

import com.dataproject.yorha.DTO.executioner.GetExecutionerDTO;
import com.dataproject.yorha.model.Executioner;
import com.dataproject.yorha.model.History;
import com.dataproject.yorha.exception.ObjectNotFoundException;
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

    public List<GetExecutionerDTO> findAll(){
        return executionerRepository.findAll().stream()
                .map(GetExecutionerDTO::new).toList();
    }

    public Optional<Executioner> findById(String id){
        return executionerRepository.findById(id);
    }

    public void createExecutioner( Executioner executioner ){

        executioner.setHistory(new ArrayList<>());
        executioner.setEquipment(armoryRepository.findAll().stream()
                .filter(weapon -> weapon.getName()
                        .equals("YoRHa-issue Blade"))
                .toList().get(0));

        executionerRepository.save(executioner);
    }

    //METHODS

    /**
     * Save the Executioner into DB. This function is called from another service.
     * @param executioner executioner to save.
     */
    public void saveExecutioner( Executioner executioner ){
        executionerRepository.save( executioner );
    }
}
