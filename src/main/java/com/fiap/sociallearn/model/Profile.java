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
import java.util.List;

@Table(name = "profile")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Profile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private boolean active = true;

  @ManyToMany
  private List<User> users;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date lastModifiedDate;

  public ProfileResponse toResponse() {
    return ProfileResponse.builder().id(getId()).name(getName()).active(isActive()).build();
  }
}
