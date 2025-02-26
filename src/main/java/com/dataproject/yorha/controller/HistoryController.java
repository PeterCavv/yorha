package com.dataproject.yorha.controller;

import com.dataproject.yorha.DTO.common.HistoryDTO;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.model.History;
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
    public ResponseEntity<HistoryDTO> getOneHistory(@PathVariable String id){

        History history = historyService.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("History not found with ID: " + id)
        );

        return ResponseEntity.ok(new HistoryDTO(history));
    }
}
