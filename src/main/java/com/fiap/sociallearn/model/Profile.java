package com.fiap.sociallearn.model;

import com.fiap.sociallearn.response.ProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

  @Id
  private String id;
  private String name;
  private boolean active;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date lastModifiedDate;

  public ProfileResponse toResponse() {
    return ProfileResponse.builder().id(getId()).name(getName()).active(isActive()).build();
  }
}
