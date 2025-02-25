package com.dataproject.yorha.DTO.operator;

import com.dataproject.yorha.DTO.android.GetAndroidDTO;
import com.dataproject.yorha.model.Operator;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetOperatorDTO implements Serializable {

    private String id;
    private String name;

    public GetOperatorDTO(Operator operator){
        this.id = operator.getId();
        this.name = operator.getName().getName();
    }
}
