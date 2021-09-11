package com.fiap.sociallearn.service;

import com.fiap.sociallearn.exceptions.ApiErrorException;
import com.fiap.sociallearn.model.ClassScheduling;
import com.fiap.sociallearn.repository.ClassSchedulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClassSchedulingService {
  @Autowired
  ClassSchedulingRepository classSchedulingRepository;

  @Autowired
  UserService userService;

  @Autowired
  LearningContentService learningContentService;

  public ClassScheduling save(ClassScheduling classScheduling) throws ApiErrorException {
    validClassScheduling(classScheduling);
    return classSchedulingRepository.save(classScheduling);
  }

  public void validClassScheduling(final ClassScheduling classScheduling)
      throws ApiErrorException {
    boolean isValidUsers = classScheduling.getUsers()
        .stream()
        .allMatch(user -> userService.findById(user.getId()) != null);
    boolean isValidLearningContentId =
        learningContentService.findById(classScheduling.getLearningContent().getId()) != null;
    if (!isValidUsers || !isValidLearningContentId) {
      throw new ApiErrorException(HttpStatus.BAD_REQUEST, "Invalid Class Scheduling Params");
    }
  }

  public ClassScheduling findById(String id) throws ApiErrorException {
    Optional<ClassScheduling> optionalCourse = classSchedulingRepository.findById(id);
    return optionalCourse.orElseThrow(() -> new ApiErrorException(HttpStatus.NOT_FOUND,
        "The informed class scheduling doesn't exists"));
  }

  public List<ClassScheduling> findAll() {
    return (List<ClassScheduling>) classSchedulingRepository.findAll();
  }

  public List<ClassScheduling> findAllByUserId(final String userId) {
    return classSchedulingRepository.findByUserId(userId);
  }

  public ClassScheduling update(String classSchedulingId, ClassScheduling updatedClassScheduling)
      throws ApiErrorException {
    var classScheduling = updateSavedClassScheduling(classSchedulingId, updatedClassScheduling);
    return classSchedulingRepository.save(classScheduling);
  }

  private ClassScheduling updateSavedClassScheduling(final String classSchedulingId,final ClassScheduling updatedClassScheduling) throws ApiErrorException {
    var savedClassScheduling = findById(classSchedulingId);
    savedClassScheduling.setDurationInHours(updatedClassScheduling.getDurationInHours());
    savedClassScheduling.setRealizationDate(updatedClassScheduling.getRealizationDate());
    savedClassScheduling.setCourseMode(updatedClassScheduling.getCourseMode());
    savedClassScheduling.setLearningContent(updatedClassScheduling.getLearningContent());
    savedClassScheduling.setUsers(updatedClassScheduling.getUsers());
    savedClassScheduling.setActive(updatedClassScheduling.isActive());
    savedClassScheduling.setLastModifiedDate(Date.from(Instant.now()));
    return savedClassScheduling;
  }

  public ClassScheduling inactivate(String classSchedulingId) throws ApiErrorException {
    var classScheduling = findById(classSchedulingId);
    classScheduling.setActive(false);
    return classSchedulingRepository.save(classScheduling);
  }

  public void deleteById(String id) {
    classSchedulingRepository.deleteById(id);
  }
}
