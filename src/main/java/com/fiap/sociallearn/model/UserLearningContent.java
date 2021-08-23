package com.fiap.sociallearn.model;

import com.fiap.sociallearn.response.UserLearningContentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Table(name = "user_learning_contents")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserLearningContent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//  @OnDelete(action = OnDeleteAction.CASCADE)
  @ManyToOne
  private User user;

//  @OnDelete(action = OnDeleteAction.CASCADE)
  @ManyToOne
  private LearningContent learningContent;

  private boolean active = true;

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
