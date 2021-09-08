package com.fiap.sociallearn.model;

import com.fiap.sociallearn.response.ContentAreaResponse;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class ContentArea {
  @Id
  private String id;
  private String name;
  private boolean active;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date lastModifiedDate;

  public ContentAreaResponse toResponse() {
    return ContentAreaResponse.builder().id(getId()).name(getName()).active(isActive()).build();
  }
}
