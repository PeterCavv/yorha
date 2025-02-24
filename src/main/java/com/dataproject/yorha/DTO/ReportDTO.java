package com.dataproject.yorha.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ReportDTO implements Serializable {

    @NotBlank(message = "The title of a Report cannot be blank")
    @Size(max = 30, min = 10, message = "Name of the Report out of length")
    private String name;

    @NotBlank(message = "The body of a Report cannot be blank")
    @Size(min = 50, max = 800, message = "Body of the Report out of length")
    private String content;

    @NotNull(message = "Publish date cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate publishDate;

    private String androidId;

}
