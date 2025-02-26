package com.dataproject.yorha.DTO.common;

import com.dataproject.yorha.model.State;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StateDTO implements Serializable {

    private String id;
    private String name;

    public StateDTO(State state){
        this.id = state.getId();
        this.name = state.getName();
    }
}
