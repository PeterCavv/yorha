package com.dataproject.yorha.controller;

import com.dataproject.yorha.entity.Armory;
import com.dataproject.yorha.service.ArmoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<Armory> getOneWeapon(@PathVariable String id){
        Optional<Armory> weapon = armoryService.findById(id);

        if(weapon.isPresent()){
            return ResponseEntity.ok(weapon.get());
        }

        return ResponseEntity.notFound().build();
    }
}
