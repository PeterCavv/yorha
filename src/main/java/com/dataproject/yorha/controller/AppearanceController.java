package com.dataproject.yorha.controller;

import com.dataproject.yorha.model.Appearance;
import com.dataproject.yorha.service.AppearanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appearances")
public class AppearanceController {
    @Autowired
    private AppearanceService appearanceService;

    @GetMapping
    public ResponseEntity<List<Appearance>> getAllAppearances(){
        return new ResponseEntity<>(appearanceService.getAllAppearances(), HttpStatus.OK);
    }
}
