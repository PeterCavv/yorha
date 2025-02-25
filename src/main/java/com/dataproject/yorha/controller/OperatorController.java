package com.dataproject.yorha.controller;

import com.dataproject.yorha.DTO.operator.GetOperatorDTO;
import com.dataproject.yorha.model.Operator;
import com.dataproject.yorha.service.OperatorService;
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
@RequestMapping("/operators")
public class OperatorController {
    @Autowired
    private OperatorService operatorService;

    @GetMapping
    public ResponseEntity<List<GetOperatorDTO>> getAllOperator(){
        return new ResponseEntity<>(operatorService.allOperator(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Operator>> getOneOperator(@PathVariable String id){
        return new ResponseEntity<>(operatorService.oneOperator(id), HttpStatus.OK);
    }
}
