package com.dataproject.yorha.DTO;

import com.dataproject.yorha.validation.model.ModelExists;
import com.dataproject.yorha.validation.type.TypeExists;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AndroidDTO implements Serializable {

    @Size(max = 35, message = "Android's name out of length: max length = 25")
    private String name;

    @NotBlank(message = "An Android must have a Model")
    @ModelExists
    private String modelId;

    @NotBlank(message = "An Android must have a Type.")
    @TypeExists
    private String typeId;

    private int type_number;

    private String appearanceId;

    private String stateId;

    private String desc;

    //This annotation is used to let Jackson uses the field name to determine the property name.
    @JsonProperty
    private boolean isOperator;

    @JsonProperty
    private boolean isExecutioner;

}
