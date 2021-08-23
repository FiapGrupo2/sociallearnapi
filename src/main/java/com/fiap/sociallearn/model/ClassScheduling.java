package com.fiap.sociallearn.model;

import com.fiap.sociallearn.response.ClassSchedulingResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "class_scheduling")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ClassScheduling {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany
  @JoinTable(name = "class_scheduling_users", joinColumns = @JoinColumn(name = "class_scheduling_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> users;

  @Enumerated(EnumType.STRING)
  private CourseMode courseMode;

//  @OnDelete(action = OnDeleteAction.CASCADE)
  @ManyToOne
  @JoinColumn(name = "learning_content_id", nullable = false)
  private LearningContent learningContent;

  private LocalDateTime realizationDate;

  private Double durationInHours;

  private boolean active = true;

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
