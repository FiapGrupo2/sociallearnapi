package com.fiap.sociallearn.service;

import com.fiap.sociallearn.model.User;
import com.fiap.sociallearn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
  @Autowired
  UserRepository userRepository;

  public List<User> findAllTeachersByLearningContent(Long learningContentId) {
    return userRepository.findAllUsersByLearningContentAndProfile(learningContentId, 2L);
  }

  public List<User> findAllStudentsByLearningContent(Long learningContentId) {
    return userRepository.findAllUsersByLearningContentAndProfile(learningContentId, 1L);
  }
}
