package com.fiap.sociallearn.request;

import com.fiap.sociallearn.model.ContentArea;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentAreaRequest {
  private String name;

  public ContentArea toEntity() {
    return ContentArea.builder()
        .name(getName())
        .active(true)
        .createdDate(Date.from(Instant.now()))
        .build();
  }
}
