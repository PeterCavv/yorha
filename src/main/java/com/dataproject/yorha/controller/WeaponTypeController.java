package com.dataproject.yorha.controller;

import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.model.WeaponType;
import com.dataproject.yorha.service.WeaponTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weapon_type")
public class WeaponTypeController {

    @Autowired
    private WeaponTypeService weaponTypeService;

    @GetMapping
    public ResponseEntity<List<WeaponType>> getAllWeaponType(){
        return new ResponseEntity<>(weaponTypeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeaponType> getOneWeaponType(@PathVariable String id){
        WeaponType weaponType = weaponTypeService.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Weapon Type not found with ID: " + id)
        );

        return ResponseEntity.ok(weaponType);
    }
}
