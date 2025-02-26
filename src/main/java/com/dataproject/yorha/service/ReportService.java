package com.dataproject.yorha.service;

import com.dataproject.yorha.DTO.report.CreateReportDTO;
import com.dataproject.yorha.DTO.report.GetReportDTO;
import com.dataproject.yorha.DTO.report.UpdateReportDTO;
import com.dataproject.yorha.model.Android;
import com.dataproject.yorha.model.Report;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<GetReportDTO> allReports() {
        return reportRepository.findAll().stream().map(GetReportDTO::new).toList();
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
     * @param createReportDto A Report to create obtained from the view.
     */
    public Report createOneReport(CreateReportDTO createReportDto) {

        Report report = new Report();

        report.setName(createReportDto.getName());
        report.setContent(createReportDto.getContent());
        report.setPublish_date(createReportDto.getPublishDate());

        report.setAndroid( new Android(createReportDto.getAndroidId()) );

        reportRepository.save(report);

        return report;
    }

    /**
     * Update the data of a report.
     * @param reportDto A Report to create obtained from the view.
     * @param reportId Report's ID
     */
    public Optional<Report> updateOneReport(UpdateReportDTO reportDto, String reportId){

        Report report = reportRepository.findById(reportId).orElseThrow(
                () -> new ObjectNotFoundException("Report not found with ID: " + reportId)
        );

        report.setName(reportDto.getName());
        report.setContent(reportDto.getContent());
        report.setPublish_date(reportDto.getPublishDate());

        reportRepository.save(report);

        return Optional.of(report);
    }

    /**
     * Remove one report from DB.
     * @param id Report's ID
     */
    public void deleteOneReport(String id){

        Report report = reportRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Report not found with ID: " + id)
        );

        reportRepository.save(report);
    }

}
