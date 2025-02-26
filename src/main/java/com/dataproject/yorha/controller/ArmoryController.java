package com.dataproject.yorha.controller;

import com.dataproject.yorha.DTO.common.ArmoryDTO;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.model.Armory;
import com.dataproject.yorha.service.ArmoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/armory")
public class ArmoryController {

    @Autowired
    private ArmoryService armoryService;

    @GetMapping
    public ResponseEntity<List<Armory>> getAllArmory(){
        return new ResponseEntity<>(armoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArmoryDTO> getOneWeapon(@PathVariable String id){
        Armory weapon = armoryService.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Weapon not found with ID: " + id)
        );

        return ResponseEntity.ok(new ArmoryDTO(weapon));
    }
}
