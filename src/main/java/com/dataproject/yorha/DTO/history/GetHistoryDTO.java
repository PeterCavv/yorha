package com.dataproject.yorha.DTO.history;

import com.dataproject.yorha.model.History;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetHistoryDTO {

    private String id;
    private String executioner;
    private String android;
    private LocalDateTime date;
    private String summary;

    public GetHistoryDTO(History history) {
        this.id = history.getId();
        this.executioner = history.getExecutioner().getName().getName();
        this.android = history.getAndroid().getName();
        this.date = history.getDate();
        this.summary = history.getSummary();

    }
}
