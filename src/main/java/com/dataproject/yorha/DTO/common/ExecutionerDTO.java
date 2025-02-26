package com.dataproject.yorha.DTO.common;

import com.dataproject.yorha.model.Executioner;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ExecutionerDTO implements Serializable {

    private String id;
    private String name;
    private String equipment;
//    private GetHistoryDTO histories;

    public ExecutionerDTO(Executioner executioner){
        this.id = executioner.getId();
        this.name = executioner.getName().getName();
        this.equipment = executioner.getEquipment().getName();
    }
}
