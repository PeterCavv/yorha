package com.dataproject.yorha.model;

import jakarta.validation.constraints.NotBlank;
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

    public Type(String typeId) {
        this.id = id;
    }
}
