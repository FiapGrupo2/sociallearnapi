package com.fiap.sociallearn.request;

import com.fiap.sociallearn.model.Gender;
import com.fiap.sociallearn.model.Profile;
import com.fiap.sociallearn.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UserRequest {
  private String name;
  private String email;
  private String password;
  private Gender gender;
  private List<Long> profilesId;

  public User toEntity() {
    return User.builder()
        .name(getName())
        .email(getEmail())
        .gender(getGender())
        .password(getPassword())
        .profiles(getProfilesId().stream()
            .map(profileId -> Profile.builder().id(profileId).build())
            .collect(Collectors.toList()))
        .active(true)
        .createdDate(Date.from(Instant.now()))
        .build();
  }
}


