package com.fiap.sociallearn.model;

import com.fiap.sociallearn.response.ContentAreaResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Table(name = "content_area")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ContentArea {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private boolean active = true;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date lastModifiedDate;

  public ContentAreaResponse toResponse() {
    return ContentAreaResponse.builder().id(getId()).name(getName()).active(isActive()).build();
  }
}
