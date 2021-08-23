package com.fiap.sociallearn.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class LearningContentResponse {
  private Long id;
  private String name;
  private List<ContentAreaResponse> contentAreas;
  private boolean active;
}
