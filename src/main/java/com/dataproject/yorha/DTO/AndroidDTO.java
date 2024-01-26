package com.dataproject.yorha.DTO;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.io.Serializable;

@Getter
@Setter
public class AndroidDTO implements Serializable {

    private String name;

    private String short_name;

    private ObjectId modelId;

    private ObjectId typeId;

    private int type_number;

    private ObjectId appearanceId;

    private ObjectId stateId;

    private String desc;

}
