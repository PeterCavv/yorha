package com.dataproject.yorha.DTO;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Getter
@Setter
public class AndroidDTO implements Serializable {

    private String name;

    private String modelId;

    private String typeId;

    private int type_number;

    private String appearanceId;

    private String stateId;

    private String desc;

    private boolean isOperator;

}
