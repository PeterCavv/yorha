package com.dataproject.yorha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "state")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class State {
    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String name;
}
