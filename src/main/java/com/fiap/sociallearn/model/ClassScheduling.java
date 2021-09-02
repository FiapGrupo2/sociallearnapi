package com.fiap.sociallearn.model;

import com.fiap.sociallearn.response.ClassSchedulingResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class ClassScheduling {
  @Id
  private String id;

  private List<User> users;

  @Enumerated(EnumType.STRING)
  private CourseMode courseMode;

  private LearningContent learningContent;

  private LocalDateTime realizationDate;

  private Double durationInHours;

  private boolean active;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date lastModifiedDate;

  public ClassSchedulingResponse toResponse() {
    return ClassSchedulingResponse.builder()
        .id(getId())
        .users(getUsers().stream().map(user -> user.toResponse()).collect(Collectors.toList()))
        .courseMode(getCourseMode())
        .durationInHours(getDurationInHours())
        .realizationDate(getRealizationDate())
        .active(isActive())
        .build();
  }
}
