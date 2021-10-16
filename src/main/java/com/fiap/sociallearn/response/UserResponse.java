package com.fiap.sociallearn.response;

import com.fiap.sociallearn.model.Gender;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserResponse {
  private Long seq;
  private String id;
  private String name;
  private String username;
  private String email;
  private String gender;
  private List<ProfileResponse> profiles;
  private boolean active;
}


