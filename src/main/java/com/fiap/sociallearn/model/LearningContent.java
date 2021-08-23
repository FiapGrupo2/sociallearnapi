package com.fiap.sociallearn.model;

import com.fiap.sociallearn.response.LearningContentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Table(name = "learning_content")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class LearningContent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

//  @OnDelete(action = OnDeleteAction.CASCADE)
  @ManyToMany
  @JoinTable(name = "learning_content_areas", joinColumns = @JoinColumn(name = "learning_content_id"), inverseJoinColumns = @JoinColumn(name = "content_area_id"))
  private List<ContentArea> contentAreas;

  private boolean active = true;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date lastModifiedDate;

  public LearningContentResponse toResponse() {
    return LearningContentResponse.builder()
        .id(getId())
        .name(getName())
        .contentAreas(getContentAreas().stream()
            .map(contentArea -> contentArea.toResponse())
            .collect(Collectors.toList()))
        .active(isActive())
        .build();
  }
}
