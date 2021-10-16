package com.fiap.sociallearn.model;

import com.fiap.sociallearn.response.LearningContentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.index.Indexed;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("learningcontents")
public class LearningContent {
  @Id
  private String id;

  private String name;

  private List<ContentArea> contentAreas;

  private boolean active;

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
