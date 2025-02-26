package com.dataproject.yorha.DTO.operator;

import com.dataproject.yorha.model.Android;
import com.dataproject.yorha.model.Operator;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class GetOperatorDTO implements Serializable {

    private String id;
    private String name;
    private List<String> androids;

    public GetOperatorDTO(Operator operator){
        this.id = operator.getId();
        this.name = operator.getName().getName();
        if( operator.getAndroids() != null ){
            this.androids = operator.getAndroids()
                    .stream().map(Android::getName).toList();
        }
    }
}
