package com.dataproject.yorha.service;

import com.dataproject.yorha.DTO.ReportDTO;
import com.dataproject.yorha.entity.Android;
import com.dataproject.yorha.entity.Report;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.repository.AndroidRepository;
import com.dataproject.yorha.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    AndroidRepository androidRepository;

    /**
     * Method to find all the Reports created.
     * @return
     */
    public List<Report> allReports() {
        return reportRepository.findAll();
    }

    /**
     * Method to get a Report with a specific ID.
     * @param id ID of the Report to get.
     * @return
     */
    public Optional<Report> findById(String id) {
        return reportRepository.findById(id);
    }

    /**
     * Method to create a Report.
     * @param reportDto A Report to create obtained from the view.
     * @return
     */
    public Report createOneReport(ReportDTO reportDto) {

        Report report = new Report();

        //Validating if everything send exist or aren't empty.
        validateReportDto(reportDto);

        report.setName(reportDto.getName());
        report.setContent(report.getContent());

        Android android = new Android();
        android.setId(reportDto.getAndroidId());
        report.setAndroid(android);

        return report;
    }

    /**
     * Method to validate the attributes of the Report.
     * @param reportDto Report obtained from the http request.
     */
    private void validateReportDto(ReportDTO reportDto){
        if( !androidRepository.existsById(reportDto.getAndroidId()) ){
            throw new ObjectNotFoundException("Android not found with ID: " + reportDto.getAndroidId());
        }

        if( reportDto.getName().trim().isBlank() ){
            //throw new Exception();
        }

        if( reportDto.getContent().trim().isBlank() ){
            //throw new Exception();
        }


    }
}
