package com.hescha.rudictionary.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Dictionary extends AbstractEntity{
    @ManyToOne
    private DictionaryType dictionaryType;
    @OneToMany
    private List<Word> words = new ArrayList<>();
}
