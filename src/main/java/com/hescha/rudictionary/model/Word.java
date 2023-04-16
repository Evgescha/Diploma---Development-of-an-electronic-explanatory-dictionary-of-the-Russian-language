package com.hescha.rudictionary.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Word extends AbstractEntity {
    private String name;
    @Column(length = 4000)
    private String description;
    @ManyToOne
    private DictionaryType dictionaryType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "word")
    private List<Comment> comments = new ArrayList<>();

    @Override
    public String toString() {
        return "Word{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dictionary=" + dictionaryType.getName() +
                ", comments=" + comments +
                '}';
    }
}
