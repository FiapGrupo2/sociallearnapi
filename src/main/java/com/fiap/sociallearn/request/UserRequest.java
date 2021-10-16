package com.fiap.sociallearn.request;

import com.fiap.sociallearn.model.Gender;
import com.fiap.sociallearn.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UserRequest {
  private String name;
  private String username;
  private String email;
  private String password;
  private String gender;

  public User toEntity() {
    return User.builder()
        .name(getName())
        .username(getUsername())
        .email(getEmail())
        .gender(getGender())
        .password(getPassword())
        .active(true)
        .createdDate(Date.from(Instant.now()))
        .build();
  }
}


