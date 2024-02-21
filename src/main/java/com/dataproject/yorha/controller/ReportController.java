package com.dataproject.yorha.controller;

import com.dataproject.yorha.DTO.ReportDTO;
import com.dataproject.yorha.entity.Report;
import com.dataproject.yorha.service.ReportService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reports")
public class ReportController {

        @Autowired
        private ReportService reportService;

        @GetMapping
        public ResponseEntity<List<Report>> getAllAndroids(){
            return new ResponseEntity<List<Report>>(reportService.allReports(), HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Report> getOneReport(@PathVariable String id){
            Optional<Report> report = reportService.findById(id);

            if( report.isPresent() ){
                return ResponseEntity.ok(report.get());
            }

            return ResponseEntity.notFound().build();
        }

        @PostMapping
        public ResponseEntity<Report> createOneReport(@RequestBody @Validated ReportDTO reportDto){

            Report report = reportService.createOneReport(reportDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(report);
        }
}
