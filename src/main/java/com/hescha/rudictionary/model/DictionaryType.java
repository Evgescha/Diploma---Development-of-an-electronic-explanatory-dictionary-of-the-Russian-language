package com.hescha.rudictionary.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class DictionaryType extends AbstractEntity{
    private String name;
}
