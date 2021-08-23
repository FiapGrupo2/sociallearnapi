package com.fiap.sociallearn.response;

import com.fiap.sociallearn.model.CourseMode;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ClassSchedulingResponse {
  private Long id;
  private List<UserResponse> users;
  private Long learningContentId;
  private CourseMode courseMode;
  private Double durationInHours;
  private LocalDateTime realizationDate;
  private boolean active;
}
