package com.dataproject.yorha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "models")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Model {
    @Id
    private ObjectId id;

    private String name;
}
