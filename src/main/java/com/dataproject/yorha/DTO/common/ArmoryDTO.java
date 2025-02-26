package com.dataproject.yorha.DTO.common;

import com.dataproject.yorha.model.Armory;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ArmoryDTO implements Serializable {

    private String id;
    private String name;
    private String desc;
    private String weaponType;

    public ArmoryDTO(Armory armory){
        this.id = armory.getId();
        this.name = armory.getName();
        this.desc = armory.getDesc();
        this.weaponType = armory.getWeapon_type().getName();
    }
}
