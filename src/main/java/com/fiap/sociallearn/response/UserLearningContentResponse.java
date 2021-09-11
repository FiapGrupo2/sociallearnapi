package com.fiap.sociallearn.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserLearningContentResponse {
  private String id;
  private UserResponse user;
  private LearningContentResponse learningContentResponse;
  private boolean active;
}
