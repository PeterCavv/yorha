package com.dataproject.yorha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String name;

    private String resume;

    private String desc;

    public Type(String name, String resume, String desc){
        this.name = name;
        this.resume = resume;
        this.desc = desc;
    }


}
