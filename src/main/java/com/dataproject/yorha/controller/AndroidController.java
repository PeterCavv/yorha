package com.dataproject.yorha.controller;

import com.dataproject.yorha.model.*;
import com.dataproject.yorha.service.AndroidService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/androids", produces = MediaType.APPLICATION_JSON_VALUE)
public class AndroidController {

    @Autowired
    private AndroidService androidService;

    @GetMapping
    public ResponseEntity<List<Android>> getAllAndroids(){
        return new ResponseEntity<List<Android>>(androidService.allAndroids(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Android>> getOneAndroide(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<Android>>(androidService.oneAndroid(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create/", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Android> createOneAndroid(@RequestBody Android android)
    {
        return new ResponseEntity<Android>(androidService.createAndroid(android), HttpStatus.OK);

    }
}
