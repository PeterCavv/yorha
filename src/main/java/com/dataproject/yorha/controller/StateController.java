package com.dataproject.yorha.controller;

import com.dataproject.yorha.model.State;
import com.dataproject.yorha.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/states")
public class StateController {
    @Autowired
    private StateService stateService;

    @GetMapping
    public ResponseEntity<List<State>> getAllState(){
        return new ResponseEntity<>(stateService.getAllState(), HttpStatus.OK);
    }

}
