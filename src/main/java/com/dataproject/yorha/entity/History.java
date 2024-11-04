package com.dataproject.yorha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Document(collection = "histories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class History{

    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @DBRef(lazy = true)
    private List<Android> androidList;

    @DateTimeFormat
    private Date date;
}
