package com.dataproject.yorha.controller;

import com.dataproject.yorha.DTO.android.CreateAndroidDTO;
import com.dataproject.yorha.DTO.android.GetAndroidDTO;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.model.*;
import com.dataproject.yorha.service.AndroidService;
import jakarta.validation.Valid;
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
@Validated
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
    public ResponseEntity<List<GetAndroidDTO>> getAllAndroids(){
        return new ResponseEntity<>(androidService.findAll(), HttpStatus.OK);
    }

    /**
     * Method to obtain an Android with a specific ID.
     * @param id The ID of the Android to obtain.
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<GetAndroidDTO> getOneAndroid(@PathVariable String id){
        Android android = androidService.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Android not found with ID: " + id)
        );

        return ResponseEntity.ok( new GetAndroidDTO(android) );
    }

    @GetMapping("/available")
    public ResponseEntity<List<GetAndroidDTO>> getAllAvailableAndroids(){
        return new ResponseEntity<>(androidService.findAllAvailable(), HttpStatus.OK);
    }

    //-------------------------------------------------------------------------------------------------
    //--- POST HTTP REQUESTS --------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------

    /**
     * Method to create an Android.
     * @param createAndroidDto Android to create obtained from the http request.
     * @return
     */
    @PostMapping
    public ResponseEntity<Android> createOneAndroid(@Valid @RequestBody CreateAndroidDTO createAndroidDto)
    {
        //Here will be created and saved the Android.
        Android android = androidService.createAndroid(createAndroidDto);
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
