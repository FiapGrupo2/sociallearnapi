package com.fiap.sociallearn.request;

import com.fiap.sociallearn.model.ContentArea;
import com.fiap.sociallearn.model.LearningContent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LearningContentRequest {
  private String name;
  private List<String> contentAreaIds;

  public LearningContent toEntity() {
    return LearningContent.builder()
        .name(getName())
        .contentAreas(getContentAreaIds().stream()
            .map(contentAreaId -> ContentArea.builder().id(contentAreaId).build())
            .collect(Collectors.toList()))
        .active(true)
        .createdDate(Date.from(Instant.now()))
        .build();
  }
}
