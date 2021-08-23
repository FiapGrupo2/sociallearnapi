package com.fiap.sociallearn.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContentAreaResponse {
  private Long id;
  private String name;
  private boolean active;
}
