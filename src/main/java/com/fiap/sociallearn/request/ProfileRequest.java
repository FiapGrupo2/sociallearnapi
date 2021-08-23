package com.fiap.sociallearn.request;

import com.fiap.sociallearn.model.Profile;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
@Builder
public class ProfileRequest {
  private String name;

  public Profile toEntity() {
    return Profile.builder()
        .name(getName())
        .active(true)
        .createdDate(Date.from(Instant.now()))
        .build();
  }
}
