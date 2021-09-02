package com.fiap.sociallearn.model;

import com.fiap.sociallearn.response.UserLearningContentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class UserLearningContent {
  @Id
  private String id;

  private User user;

  private LearningContent learningContent;

  private boolean active;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date lastModifiedDate;

  public UserLearningContentResponse toResponse() {
    return UserLearningContentResponse.builder()
        .id(getId())
        .user(getUser().toResponse())
        .learningContentResponse(getLearningContent().toResponse())
        .active(isActive())
        .build();
  }
}
