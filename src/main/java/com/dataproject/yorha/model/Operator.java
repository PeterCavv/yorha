package com.dataproject.yorha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "operators")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operator {

    @Id
    private ObjectId id;

    @DBRef
    private Android name;

    @DBRef
    private List<Android> androids;

    public Operator(Android name) {
        this.name = name;
    }
}
