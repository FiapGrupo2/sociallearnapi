package com.fiap.sociallearn.request;

import com.fiap.sociallearn.model.LearningContent;
import com.fiap.sociallearn.model.User;
import com.fiap.sociallearn.model.UserLearningContent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLearningContentRequest {
  private String userId;
  private String learningContentId;

  public UserLearningContent toEntity() {
    return UserLearningContent.builder()
        .user(User.builder().id(getUserId()).build())
        .learningContent(LearningContent.builder().id(getLearningContentId()).build())
        .active(true)
        .createdDate(Date.from(Instant.now()))
        .build();
  }
}
