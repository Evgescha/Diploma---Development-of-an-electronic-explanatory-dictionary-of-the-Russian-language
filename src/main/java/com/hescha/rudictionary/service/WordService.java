package com.hescha.rudictionary.service;

import com.hescha.rudictionary.model.Comment;
import com.hescha.rudictionary.repository.*;
import com.hescha.rudictionary.model.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordService extends CrudService<Word> {

    private final WordRepository repository;

    private final CommentService commentService;
    public WordService(WordRepository repository,
                       CommentService commentService) {
        super(repository);
        this.repository = repository;
        this.commentService = commentService;
    }

    public Page<Word> readAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Word> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Word> findByNameContains(String name) {
        return repository.findByNameContaining(name);
    }

    public List<Word> findByDescription(String description) {
        return repository.findByDescription(description);
    }

    public List<Word> findByDescriptionContains(String description) {
        return repository.findByDescriptionContains(description);
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
    }
}
