package com.fiap.sociallearn.repository;

import com.fiap.sociallearn.model.UserLearningContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLearningContentRepository extends CrudRepository<UserLearningContent, Long> {
}
