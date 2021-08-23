package com.fiap.sociallearn.model;

import com.fiap.sociallearn.response.UserResponse;
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

@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  private String password;

  @Column(name = "gender", nullable = false)
  @Enumerated(EnumType.STRING)
  private Gender gender;

//  @OnDelete(action = OnDeleteAction.CASCADE)
  @ManyToMany
  @JoinTable(name = "user_profiles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "profile_id"))
  private List<Profile> profiles;

  private boolean active = true;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date lastModifiedDate;

  public UserResponse toResponse() {
    return UserResponse.builder()
        .id(getId())
        .name(getName())
        .email(getEmail())
        .gender(getGender())
        .profiles(getProfiles().stream()
            .map(profile -> profile.toResponse())
            .collect(Collectors.toList()))
        .active(isActive())
        .build();
  }
}


