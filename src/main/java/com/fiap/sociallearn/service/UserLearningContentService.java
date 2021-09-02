package com.fiap.sociallearn.service;

import com.fiap.sociallearn.exceptions.ApiErrorException;
import com.fiap.sociallearn.model.UserLearningContent;
import com.fiap.sociallearn.repository.UserLearningContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserLearningContentService {
  @Autowired
  UserLearningContentRepository userLearningContentRepository;

  @Autowired
  UserService userService;

  @Autowired
  LearningContentService learningContentService;

  public UserLearningContent save(UserLearningContent userLearningContent)
      throws ApiErrorException {
    valid(userLearningContent);
    return userLearningContentRepository.save(userLearningContent);
  }

  private void valid(final UserLearningContent userLearningContent) throws ApiErrorException {
    boolean isValidUser = userService.findById(userLearningContent.getUser().getId()) != null;
    boolean isValidLearningContent =
        learningContentService.findById(userLearningContent.getLearningContent().getId()) != null;
    if (!isValidUser || !isValidLearningContent) {
      throw new ApiErrorException(HttpStatus.BAD_REQUEST, "Invalid request");
    }
  }

  public UserLearningContent findById(String id) throws ApiErrorException {
    Optional<UserLearningContent> optionalUserLearningContent =
        userLearningContentRepository.findById(id);
    return optionalUserLearningContent.orElseThrow(() -> new ApiErrorException(HttpStatus.NOT_FOUND,
        "The informed relationship user - learning_content doesn't exists"));
  }

  public List<UserLearningContent> findAll() {
    return (List<UserLearningContent>) userLearningContentRepository.findAll();
  }

  public void deleteById(String id) {
    userLearningContentRepository.deleteById(id);
  }
}
