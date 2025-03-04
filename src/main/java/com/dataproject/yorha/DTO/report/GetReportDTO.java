package com.dataproject.yorha.DTO.report;

import com.dataproject.yorha.model.Report;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class GetReportDTO implements Serializable {

    private String id;
    private String name;
    private String content;
    private LocalDate publishDate;
    private String android;

    public GetReportDTO(Report report){
        this.id = report.getId();
        this.name = report.getName();
        this.content = report.getContent();
        this.publishDate = report.getPublish_date();
        this.android = report.getAndroid().getName();
    }

}
