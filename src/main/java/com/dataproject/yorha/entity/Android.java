package com.dataproject.yorha.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.List;

@Document(collection = "androids")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Android {

    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Indexed(unique = true)
    private String name;

    private String short_name;

    @DBRef(lazy = true)
    private Model model;

    @DBRef(lazy = true)
    private Type type;

    private int type_number;

    @DBRef(lazy = true)
    private Appearance appearance;

    @DBRef(lazy = true)
    private State state;

    private String desc;

    @DBRef(lazy = true)
    @Field(write = Field.Write.ALWAYS)
    private Operator assigned_operator;

    //private List<User> user

    public Android(String name, String short_name, Model model, Type type,
                   int type_number, Appearance appearance, State state, String desc) {
        this.name = name;
        this.short_name = short_name;
        this.model = model;
        this.type = type;
        this.type_number = type_number;
        this.appearance = appearance;
        this.state = state;
        this.desc = desc;
    }

}
