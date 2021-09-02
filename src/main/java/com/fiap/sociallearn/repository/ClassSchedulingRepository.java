package com.fiap.sociallearn.repository;

import com.fiap.sociallearn.model.ClassScheduling;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassSchedulingRepository extends MongoRepository<ClassScheduling, String> {
  @Query(value = "{'users.id': ?0")
  List<ClassScheduling> findByUserId(String userId);
}
