package com.hescha.rudictionary.service;

import com.hescha.rudictionary.model.DictionaryType;
import com.hescha.rudictionary.repository.*;
import com.hescha.rudictionary.model.Dictionary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryService extends CrudService<Dictionary> {

    private final DictionaryRepository repository;

    public DictionaryService(DictionaryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Dictionary findByDictionaryType(DictionaryType dictionaryType) {
        return repository.findByDictionaryType(dictionaryType);
    }

    public List<Dictionary> findByWordsContains(com.hescha.rudictionary.model.Word words) {
        return repository.findByWordsContains(words);
    }


    public Dictionary update(Long id, Dictionary entity) {
        Dictionary read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity Dictionary not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(Dictionary entity, Dictionary read) {
        read.setDictionaryType(entity.getDictionaryType());
        read.setWords(entity.getWords());
    }
}
