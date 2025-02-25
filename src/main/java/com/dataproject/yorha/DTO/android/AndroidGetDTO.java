package com.dataproject.yorha.DTO.android;

import com.dataproject.yorha.DTO.type.TypeDTO;
import com.dataproject.yorha.model.Android;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AndroidGetDTO implements Serializable {

    private String id;

    private String name;

    private String shortName;

    private int typeNumber;

    private String model;

    private TypeDTO type;

    private String appearance;

    private String desc;

    public AndroidGetDTO(Android android){
        this.id = android.getId();
        this.name = android.getName();
        if (android.getType() != null) {
            this.type = new TypeDTO(android.getType());
        }
        this.desc = android.getDesc();
        this.shortName = android.getShort_name();
        this.typeNumber = android.getType_number();
    }

}
