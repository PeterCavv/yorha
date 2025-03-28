package com.dataproject.yorha.controller;

import com.dataproject.yorha.DTO.common.ExecutionerDTO;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.model.Executioner;
import com.dataproject.yorha.service.ExecutionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/executioners", produces =  MediaType.APPLICATION_JSON_VALUE)
public class ExecutionerController {

    @Autowired
    public ExecutionerService executionerService;

    @GetMapping
    public ResponseEntity<List<ExecutionerDTO>> getAllExecutioner(){
        return new ResponseEntity<>(executionerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExecutionerDTO> getOneExecutioner(@PathVariable String id){
        Executioner executioner = executionerService.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Executioner not found with ID: " + id)
        );

        return ResponseEntity.ok( new ExecutionerDTO(executioner) );
    }
}
