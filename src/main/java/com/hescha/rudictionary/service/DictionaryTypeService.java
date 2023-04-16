package com.hescha.rudictionary.service;

import com.hescha.rudictionary.repository.*;
import com.hescha.rudictionary.model.DictionaryType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryTypeService extends CrudService<DictionaryType> {

    private final DictionaryTypeRepository repository;

    public DictionaryTypeService(DictionaryTypeRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public DictionaryType update(Long id, DictionaryType entity) {
        DictionaryType read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity DictionaryType not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(DictionaryType entity, DictionaryType read) {
        read.setName(entity.getName());
    }
}
