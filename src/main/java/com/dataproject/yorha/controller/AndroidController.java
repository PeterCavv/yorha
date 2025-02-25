package com.dataproject.yorha.controller;

import com.dataproject.yorha.DTO.AndroidDTO;
import com.dataproject.yorha.model.*;
import com.dataproject.yorha.service.AndroidService;
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

    //-------------------------------------------------------------------------------------------------
    //--- GET HTTP REQUESTS ---------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------

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
    public ResponseEntity<Android> getOneAndroid(@PathVariable String id){
        Optional<Android> android = androidService.findById(id);

        //Check if the ID exist.
        if(android.isPresent()){
            return ResponseEntity.ok(android.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/available")
    public ResponseEntity<List<Android>> getAllAvailableAndroids(){
        return new ResponseEntity<>(androidService.findAllAvailable(), HttpStatus.OK);
    }

    //-------------------------------------------------------------------------------------------------
    //--- POST HTTP REQUESTS --------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------

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

    //-------------------------------------------------------------------------------------------------
    //--- PUT HTTP REQUESTS ---------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------

    @PutMapping("/add/{idAndroid}/{idOperator}")
    public ResponseEntity<Optional<Android>> updateOneAndroid(@PathVariable("idAndroid") String idAndroid,
                                                    @PathVariable("idOperator") String idOperator){
        Optional<Android> android = androidService.addAssignedAndroid( idAndroid, idOperator );
        return ResponseEntity.status(HttpStatus.OK).body(android);
    }

    @PutMapping("/remove/{idAndroid}/{idOperator}")
    public ResponseEntity<Optional<Android>> deleteAssignedAndroid(@PathVariable("idAndroid") String idAndroid,
                                                              @PathVariable("idOperator") String idOperator){
        Optional<Android> android = androidService.deleteAssignedAndroid( idAndroid, idOperator );
        return ResponseEntity.status(HttpStatus.OK).body(android);
    }

    @PutMapping("/execute/{idAndroid}/{idExecutioner}")
    public ResponseEntity<Optional<Android>> executeAndroid(@PathVariable("idAndroid") String idAndroid,
                                                            @PathVariable("idExecutioner") String idExecutioner){
        Optional<Android> android = androidService.executeAndroid( idAndroid, idExecutioner );
        return ResponseEntity.status(HttpStatus.OK).body(android);
    }
}
