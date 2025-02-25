package com.dataproject.yorha.controller;

import com.dataproject.yorha.model.Model;
import com.dataproject.yorha.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/models")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @GetMapping
    public ResponseEntity<List<Model>> getAllModels(){
        return new ResponseEntity<>(modelService.getAllModels(), HttpStatus.OK);
    }
}
