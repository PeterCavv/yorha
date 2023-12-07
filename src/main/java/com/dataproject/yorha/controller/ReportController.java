package com.dataproject.yorha.controller;

import com.dataproject.yorha.model.Report;
import com.dataproject.yorha.service.ReportService;
import org.bson.types.ObjectId;
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
@RequestMapping("/api/v1/reports")
public class ReportController {

        @Autowired
        private ReportService reportService;

        @GetMapping
        public ResponseEntity<List<Report>> getAllAndroids(){
            return new ResponseEntity<List<Report>>(reportService.allReports(), HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Optional<Report>> getOneAndroide(@PathVariable ObjectId id){
            return new ResponseEntity<Optional<Report>>(reportService.oneReport(id), HttpStatus.OK);
        }
}
