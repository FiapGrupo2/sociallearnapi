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

  public List<User> findAllTeachersByLearningContent(String learningContentId) {
    return userRepository.findByLearningContentIdAndProfileId(learningContentId, "TEA");
  }

  public List<User> findAllStudentsByLearningContent(String learningContentId) {
    return userRepository.findByLearningContentIdAndProfileId(learningContentId, "STU");
  }
}
