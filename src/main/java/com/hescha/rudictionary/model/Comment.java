package com.hescha.rudictionary.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Comment extends AbstractEntity {
    private String authorName;
    private String message;
    @ManyToOne
    private Word word;
}
