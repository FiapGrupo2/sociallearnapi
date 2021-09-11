package com.fiap.sociallearn.request;

import com.fiap.sociallearn.model.ClassScheduling;
import com.fiap.sociallearn.model.CourseMode;
import com.fiap.sociallearn.model.LearningContent;
import com.fiap.sociallearn.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ClassSchedulingRequest {
  private List<String> usersIdList;
  private String learningContentId;
  private CourseMode courseMode;
  private Double durationInHours;
  private LocalDateTime realizationDate;

  public ClassScheduling toEntity() {
    return ClassScheduling.builder()
        .users(getUsersIdList().stream()
            .map(userId -> User.builder().id(userId).build())
            .collect(Collectors.toList()))
        .learningContent(LearningContent.builder().id(getLearningContentId()).build())
        .durationInHours(getDurationInHours())
        .courseMode(getCourseMode())
        .realizationDate(getRealizationDate())
        .active(true)
        .createdDate(Date.from(Instant.now()))
        .build();
  }
}
