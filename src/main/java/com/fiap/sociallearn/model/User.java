package com.fiap.sociallearn.model;

import com.fiap.sociallearn.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.index.Indexed;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
  @Id
  private String id;
  private String name;
  private String email;
  private String password;

  private Gender gender;

  private List<LearningContent> learningContents;

  private boolean active;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date lastModifiedDate;

  public UserResponse toResponse() {
    return UserResponse.builder()
        .id(getId())
        .name(getName())
        .email(getEmail())
        .gender(getGender())
        .active(isActive())
        .build();
  }
}


