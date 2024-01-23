package com.dataproject.yorha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "reports")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    private ObjectId id;

    private String name;

    private String content;

    private Android android;
}
