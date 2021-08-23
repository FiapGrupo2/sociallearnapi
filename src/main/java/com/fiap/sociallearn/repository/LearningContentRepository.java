package com.fiap.sociallearn.repository;

import com.fiap.sociallearn.model.LearningContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningContentRepository extends CrudRepository<LearningContent, Long> {
}
