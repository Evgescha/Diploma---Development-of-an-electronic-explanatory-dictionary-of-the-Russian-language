package com.hescha.rudictionary.repository;

import com.hescha.rudictionary.model.DictionaryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictionaryTypeRepository extends JpaRepository<DictionaryType, Long> {
}
