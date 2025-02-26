package com.dataproject.yorha.DTO.common;

import com.dataproject.yorha.model.History;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HistoryDTO {

    private String id;
    private String executioner;
    private String android;
    private LocalDate date;
    private String summary;

    public HistoryDTO(History history) {
        this.id = history.getId();
        this.executioner = history.getExecutioner().getName().getName();
        this.android = history.getAndroid().getName();
        this.date = history.getDate();
        this.summary = history.getSummary();

    }
}
