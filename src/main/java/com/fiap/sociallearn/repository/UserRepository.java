package com.fiap.sociallearn.repository;

import com.fiap.sociallearn.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {  
  @Query(value = "{'learningContents.id': ?0, 'profiles.id' : ?1}")
  List<User> findByLearningContentIdAndProfileId(String learningContentId, String userProfileId);
}
