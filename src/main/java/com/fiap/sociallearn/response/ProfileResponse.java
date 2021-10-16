package com.fiap.sociallearn.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProfileResponse {
  private String id;
  private String name;
  private boolean active;
}
