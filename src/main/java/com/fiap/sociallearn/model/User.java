package com.fiap.sociallearn.model;

import com.fiap.sociallearn.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

  @Transient
  public static final String SEQUENCE_NAME = "users_sequence";

  //Used for Id relational like db sequence
  private long seq;

  @Id
  private String id;
  private String name;
  private String email;
  private String password;

  private Gender gender;

  private List<Profile> profiles;
  private List<LearningContent> learningContents;

  private boolean active;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date lastModifiedDate;

  public UserResponse toResponse() {
    return UserResponse.builder()
        .id(getId())
        .seq(getSeq())
        .name(getName())
        .email(getEmail())
        .gender(getGender())
        .profiles(getProfiles().stream()
            .map(profile -> profile.toResponse())
            .collect(Collectors.toList()))
        .active(isActive())
        .build();
  }
}


