package com.hescha.rudictionary.repository;

import com.hescha.rudictionary.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findByName(String name);

    List<Word> findByNameContaining(String name);

    List<Word> findByDescription(String description);

    List<Word> findByDescriptionContains(String description);

    List<Word> findByCommentsContains(com.hescha.rudictionary.model.Comment comments);
}
