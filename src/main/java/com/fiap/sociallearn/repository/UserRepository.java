package com.fiap.sociallearn.repository;

import com.fiap.sociallearn.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  @Query(value = "select * from user u inner join user_profiles up on up.user_id = u.id left join user_learning_contents ulc on ulc.user_id = u.id  where ulc.learning_content_id = :learningContentId and up.profile_id = :userProfileId", nativeQuery = true)
  List<User> findAllUsersByLearningContentAndProfile(
      @Param("learningContentId") Long learningContentId,
      @Param("userProfileId") Long userProfileId);
}
