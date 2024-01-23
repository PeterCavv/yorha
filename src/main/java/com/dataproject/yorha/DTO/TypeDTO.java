package com.dataproject.yorha.DTO;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class TypeDTO implements Serializable {

    private String name;

    private String resume;

    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
