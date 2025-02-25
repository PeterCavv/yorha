package com.dataproject.yorha.controller;

import com.dataproject.yorha.DTO.report.ReportCreateDTO;
import com.dataproject.yorha.DTO.report.ReportUpdateDTO;
import com.dataproject.yorha.model.Report;
import com.dataproject.yorha.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reports")
@Validated
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
        public ResponseEntity<Report> createOneReport(@Valid @RequestBody ReportCreateDTO reportDto){

            Report report = reportService.createOneReport(reportDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(report);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Optional<Report>> updateOneReport(@PathVariable("id") String reportId,
                                                                @Valid @RequestBody ReportUpdateDTO reportDto){


            Optional<Report> report = reportService.updateOneReport(reportDto, reportId);
            return ResponseEntity.status(HttpStatus.OK).body(report);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteReport(@PathVariable("id") String id){

            reportService.deleteOneReport(id);
            return ResponseEntity.status(HttpStatus.OK).build();

        }
}
