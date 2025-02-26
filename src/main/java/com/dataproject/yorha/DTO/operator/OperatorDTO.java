package com.dataproject.yorha.DTO.operator;

import com.dataproject.yorha.model.Android;
import com.dataproject.yorha.model.Operator;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class OperatorDTO implements Serializable {

    private String id;
    private String name;

    public OperatorDTO(Operator operator){
        this.id = operator.getId();
        this.name = operator.getName().getName();
    }
}