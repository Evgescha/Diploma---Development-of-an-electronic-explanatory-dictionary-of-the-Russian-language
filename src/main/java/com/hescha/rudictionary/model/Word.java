package com.hescha.rudictionary.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Word extends AbstractEntity {
    private String name;
    private String description;
    @ManyToOne
    private Dictionary dictionary;
    @OneToMany
    private List<Comment> comments = new ArrayList<>();
}
