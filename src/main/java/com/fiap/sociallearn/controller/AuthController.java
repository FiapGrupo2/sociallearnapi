package com.fiap.sociallearn.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fiap.sociallearn.model.User;
import com.fiap.sociallearn.request.LoginRequest;
//import com.fiap.sociallearn.request.SignupRequest;
import com.fiap.sociallearn.response.JwtResponse;
//import com.fiap.sociallearn.response.MessageResponse;
import com.fiap.sociallearn.repository.UserRepository;
import com.fiap.sociallearn.security.JwtUtils;
import com.fiap.sociallearn.security.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

    @PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		String type = "Bearer";
        
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String id = userDetails.getId();
        String username = userDetails.getUsername();
        String email = userDetails.getEmail();
        JwtResponse response = new JwtResponse(jwt, id, username, email, type);
		
		return ResponseEntity.ok(response);
	}
}
