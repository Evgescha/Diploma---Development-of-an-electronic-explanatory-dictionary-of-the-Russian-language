package com.hescha.rudictionary.repository;

import com.hescha.rudictionary.model.Comment;
import com.hescha.rudictionary.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
