package com.hescha.rudictionary.repository;

import com.hescha.rudictionary.model.Dictionary;
import com.hescha.rudictionary.model.DictionaryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
    Dictionary findByDictionaryType(DictionaryType dictionaryType);

    List<Dictionary> findByWordsContains(com.hescha.rudictionary.model.Word words);
}
