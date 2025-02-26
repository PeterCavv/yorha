package com.dataproject.yorha.DTO.common;

import com.dataproject.yorha.model.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ModelDTO implements Serializable {

    private String id;
    private String name;

    public ModelDTO(Model model){
        this.id = model.getId();
        this.name = model.getName();
    }
}
