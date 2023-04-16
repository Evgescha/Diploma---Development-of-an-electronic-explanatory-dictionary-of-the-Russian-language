package com.hescha.rudictionary.service;

import com.hescha.rudictionary.repository.*;
import com.hescha.rudictionary.model.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService extends CrudService<Word> {

    private final WordRepository repository;

    public WordService(WordRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Page<Word> readAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Word> findByNameContains(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Word update(Long id, Word entity) {
        Word read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity Word not found");
        }
        updateFields(entity, read);
        return update(read);
    }

    private void updateFields(Word entity, Word read) {
        read.setName(entity.getName());
        read.setDescription(entity.getDescription());
    }
}
