package com.dataproject.yorha.DTO;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TypeDTO implements Serializable {

    private String name;

    private String resume;

    private String desc;

}
