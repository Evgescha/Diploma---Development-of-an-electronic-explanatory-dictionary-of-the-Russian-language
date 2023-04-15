package com.hescha.rudictionary.repository;

import com.hescha.rudictionary.model.Comment;
import com.hescha.rudictionary.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByAuthorName(String authorName);

    List<Comment> findByAuthorNameContains(String authorName);

    List<Comment> findByMessage(String message);

    List<Comment> findByMessageContains(String message);

    Comment findByWord(Word word);
}
