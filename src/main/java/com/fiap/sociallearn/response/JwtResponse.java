package com.fiap.sociallearn.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtResponse {
    private String access_token;
	private String id;
	private String username;
	private String email;
	private String type;
}
