package com.dataproject.yorha.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document(collection = "appearances")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appearance {

    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String name;

    public Appearance(String id) {
        this.id = id;
    }
}
