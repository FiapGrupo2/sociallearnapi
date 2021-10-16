package com.fiap.sociallearn.request;

import com.fiap.sociallearn.model.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	private String username;
	private String password;

    public User toEntity() {
        return User.builder()
            .username(getUsername())
            .password(getPassword())
            .active(true)
            .build();
      }

}
