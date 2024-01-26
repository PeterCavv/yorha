package com.dataproject.yorha.entity;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "androids")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Android {

    @Id
    private ObjectId id;

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

    //private List<User> user

    public Android(String name, String short_name, Model model, Type type, int type_number, Appearance appearance, State state,
                   String desc) {
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
