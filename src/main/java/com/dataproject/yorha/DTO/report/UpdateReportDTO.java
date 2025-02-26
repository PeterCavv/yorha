package com.dataproject.yorha.DTO.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class UpdateReportDTO implements Serializable {

    @NotBlank(message = "A name needs to be given to the report.")
    @Size(max = 30, min = 10, message = "Name of the Report out of length: needs to be between 10 ~ 30")
    private String name;

    @NotBlank(message = "The report needs content.")
    @Size(min = 50, max = 800, message = "Body of the Report out of length: needs to be between 50 ~ 800")
    private String content;

    @NotNull(message = "Date cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate publishDate;

}