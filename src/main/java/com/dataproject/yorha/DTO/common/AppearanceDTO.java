package com.dataproject.yorha.DTO.common;

import com.dataproject.yorha.model.Appearance;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AppearanceDTO implements Serializable {

    private String id;
    private String name;

    public AppearanceDTO(Appearance appearance){
        this.id = appearance.getId();
        this.name = appearance.getName();
    }
}
