package com.dataproject.yorha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document(collection = "executioners")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Executioner {

    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @DBRef(lazy = true)
    private Android name;

    @DBRef(lazy = true)
    private Armory equipment;

    @DBRef(lazy = true)
    private List<History> history;
}