package com.dataproject.yorha.DTO.type;


import com.dataproject.yorha.model.Type;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TypeDTO implements Serializable {

    private String id;
    private String name;

    public TypeDTO(Type type){
        this.id = type.getId();
        this.name = type.getName();
    }

}
