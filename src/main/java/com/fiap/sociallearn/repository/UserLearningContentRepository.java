package com.fiap.sociallearn.repository;

import com.fiap.sociallearn.model.UserLearningContent;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLearningContentRepository extends MongoRepository<UserLearningContent, String> {
}
