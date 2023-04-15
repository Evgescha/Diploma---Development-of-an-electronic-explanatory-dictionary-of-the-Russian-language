package com.hescha.rudictionary.service;

import com.hescha.rudictionary.model.Dictionary;
import com.hescha.rudictionary.repository.*;
import com.hescha.rudictionary.model.Word;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService extends CrudService<Word> {

    private final WordRepository repository;

    public WordService(WordRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Word> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Word> findByNameContains(String name) {
        return repository.findByNameContains(name);
    }

    public List<Word> findByDescription(String description) {
        return repository.findByDescription(description);
    }

    public List<Word> findByDescriptionContains(String description) {
        return repository.findByDescriptionContains(description);
    }

    public Word findByDictionary(Dictionary dictionary) {
        return repository.findByDictionary(dictionary);
    }

    public List<Word> findByCommentsContains(com.hescha.rudictionary.model.Comment comments) {
        return repository.findByCommentsContains(comments);
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
        read.setDictionary(entity.getDictionary());
        read.setComments(entity.getComments());
    }
}
