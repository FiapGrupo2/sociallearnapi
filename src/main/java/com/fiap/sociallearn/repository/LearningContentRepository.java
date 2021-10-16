package com.fiap.sociallearn.repository;

import com.fiap.sociallearn.model.LearningContent;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningContentRepository extends MongoRepository<LearningContent, String> {
}
