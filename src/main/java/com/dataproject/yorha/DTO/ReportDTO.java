package com.dataproject.yorha.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ReportDTO implements Serializable {

    private String id;

    private String name;

    private String content;

    private String androidId;

}
