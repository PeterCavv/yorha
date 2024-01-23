package com.dataproject.yorha.service;

import com.dataproject.yorha.entity.Report;
import com.dataproject.yorha.repository.ReportRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    public List<Report> allReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> oneReport(ObjectId id) {
        return reportRepository.findById(id);
    }
}
