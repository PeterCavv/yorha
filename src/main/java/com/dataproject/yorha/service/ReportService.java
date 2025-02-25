package com.dataproject.yorha.service;

import com.dataproject.yorha.DTO.ReportDTO;
import com.dataproject.yorha.model.Android;
import com.dataproject.yorha.model.Report;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    /**
     * Method to find all the Reports created.
     */
    public List<Report> allReports() {
        return reportRepository.findAll();
    }

    /**
     * Method to get a Report with a specific ID.
     * @param id ID of the Report to get.
     */
    public Optional<Report> findById(String id) {
        return reportRepository.findById(id);
    }

    /**
     * Method to create a Report.
     * @param reportDto A Report to create obtained from the view.
     */
    public Report createOneReport(ReportDTO reportDto) {

        Report report = new Report();

        report.setName(reportDto.getName());
        report.setContent(reportDto.getContent());
        report.setPublish_date(reportDto.getPublishDate());

        Android android = new Android();
        android.setId( reportDto.getAndroidId() );
        report.setAndroid(android);

        reportRepository.save(report);

        return report;
    }

    /**
     * Update the data of a report.
     * @param reportDto A Report to create obtained from the view.
     * @param reportId Report's ID
     */
    public Optional<Report> updateOneReport(ReportDTO reportDto, String reportId){

        validateReportId(reportId);

        Optional<Report> report = reportRepository.findById(reportId);

        report.ifPresent(rep -> {
            rep.setName(reportDto.getName());
            rep.setContent(reportDto.getContent());
            rep.setPublish_date(reportDto.getPublishDate());

            reportRepository.save(rep);
        });

        return report;
    }

    public void deleteOneReport(String id){

        validateReportId(id);

        Optional<Report> report = reportRepository.findById(id);

        report.ifPresent(rep -> {
            reportRepository.delete(rep);
        });
    }

    /**
     * Validate if the report exists.
     * @param reportId Report's ID.
     */
    private void validateReportId(String reportId){
        if( !reportRepository.existsById(reportId) ){
            throw new ObjectNotFoundException("Report not found with ID: " + reportId);
        }
    }


}
