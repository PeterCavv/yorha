package com.dataproject.yorha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    @Id
    private ObjectId id;

    private String name;

    private String resume;

    private String desc;

    public Type(String name, String resume, String desc){
        this.name = name;
        this.resume = resume;
        this.desc = desc;
    }


}
