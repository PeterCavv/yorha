package com.dataproject.yorha.controller;

import com.dataproject.yorha.DTO.AndroidDTO;
import com.dataproject.yorha.entity.*;
import com.dataproject.yorha.service.AndroidService;
import com.dataproject.yorha.service.TypeService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
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
    private TypeService typeService;

    @Autowired
    private AndroidService androidService;

    /**
     * Method to get all the Androids created.
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Android>> getAllAndroids(){
        return new ResponseEntity<List<Android>>(androidService.findAll(), HttpStatus.OK);
    }

    /**
     * Method to obtain an Android with a specific ID.
     * @param id The ID of the Android to obtain.
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Android> getOneAndroide(@PathVariable String id){
        Optional<Android> android = androidService.findById(id);

        //Check if the ID exist.
        if(android.isPresent()){
            return ResponseEntity.ok(android.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Method to create an Android.
     * @param androidDto Android to create obtained from the http request.
     * @return
     */
    @PostMapping
    public ResponseEntity<Android> createOneAndroid(@RequestBody @Validated AndroidDTO androidDto)
    {
        //Here will be created and saved the Android.
        Android android = androidService.createAndroid(androidDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(android);

    }
}
