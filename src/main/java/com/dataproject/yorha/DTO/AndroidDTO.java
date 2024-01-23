package com.dataproject.yorha.DTO;

import com.dataproject.yorha.entity.Appearance;
import com.dataproject.yorha.entity.Model;
import com.dataproject.yorha.entity.State;
import com.dataproject.yorha.entity.Type;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AndroidDTO implements Serializable {

    private String name;

    private String short_name;

    private Model model;

    private Type type;

    private int type_number;

    private Appearance appearance;

    private State state;

    private String desc;

}
