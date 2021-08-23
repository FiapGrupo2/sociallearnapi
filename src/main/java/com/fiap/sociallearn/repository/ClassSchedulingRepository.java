package com.fiap.sociallearn.repository;

import com.fiap.sociallearn.model.ClassScheduling;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassSchedulingRepository extends CrudRepository<ClassScheduling, Long> {
  @Query(value = "select * from class_scheduling cs inner join class_scheduling_users csu on csu.class_scheduling_id = cs.id inner join user u on u.id = csu.user_id where u.id = :userId", nativeQuery = true)
  List<ClassScheduling> findAllByUserId(@Param("userId") Long userId);
}
