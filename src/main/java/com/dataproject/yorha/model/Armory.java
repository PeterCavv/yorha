package com.dataproject.yorha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "armory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Armory {

    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String name;

    @DBRef(lazy = true)
    private WeaponType weapon_type;

    private int attack;

    private String desc;
}
