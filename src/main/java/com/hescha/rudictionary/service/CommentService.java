package com.hescha.rudictionary.service;

import com.hescha.rudictionary.model.Word;
import com.hescha.rudictionary.repository.*;
import com.hescha.rudictionary.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService extends CrudService<Comment> {

    private final CommentRepository repository;

    public CommentService(CommentRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Comment update(Long id, Comment entity) {
        Comment read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity Comment not found");
        }
        updateFields(entity, read);
        return update(read);
    }

    private void updateFields(Comment entity, Comment read) {
        read.setAuthorName(entity.getAuthorName());
        read.setMessage(entity.getMessage());
        read.setWord(entity.getWord());
    }
}
