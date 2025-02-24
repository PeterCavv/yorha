package com.dataproject.yorha.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AndroidDTO implements Serializable {

    @Size(max = 35, message = "Android's name out of length")
    private String name;

    private String modelId;

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
