package com.dataproject.yorha.controller;

import com.dataproject.yorha.DTO.AndroidDTO;
import com.dataproject.yorha.entity.*;
import com.dataproject.yorha.service.AndroidService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/androids", produces = MediaType.APPLICATION_JSON_VALUE)
public class AndroidController {

    @Autowired
    private AndroidService androidService;

    @GetMapping
    public ResponseEntity<List<Android>> getAllAndroids(){
        return new ResponseEntity<List<Android>>(androidService.allAndroids(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Android> getOneAndroide(@PathVariable ObjectId id){
        Optional<Android> android = androidService.oneAndroid(id);

        if(android.isPresent()){
            return ResponseEntity.ok(android.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Android> createOneAndroid(@RequestBody @Validated AndroidDTO androidDTO)
    {
        Android android = androidService.createAndroid(androidDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(android);

    }
}
