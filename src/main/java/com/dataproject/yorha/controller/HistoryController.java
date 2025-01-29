package com.dataproject.yorha.controller;

import com.dataproject.yorha.entity.History;
import com.dataproject.yorha.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/histories", produces =  MediaType.APPLICATION_JSON_VALUE)
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping
    public ResponseEntity<List<History>> getAllHistories(){
        return new ResponseEntity<List<History>>(historyService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<History> getOneHistory(@PathVariable String id){

        Optional<History> history = historyService.findById(id);

        //Check if the ID exist.
            if(history.isPresent()){
            return ResponseEntity.ok(history.get());
        }
            return ResponseEntity.notFound().build();
    }
}
