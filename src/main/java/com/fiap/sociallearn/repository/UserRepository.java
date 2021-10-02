package com.fiap.sociallearn.repository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fiap.sociallearn.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  @Query(value = "{'learningContents.id': ?0, 'profiles.id' : ?1}")
  List<User> findByLearningContentIdAndProfileId(String learningContentId, String userProfileId);

  User findByEmail(String email);

  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
