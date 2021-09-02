package com.fiap.sociallearn.controller;

import com.fiap.sociallearn.exceptions.ApiErrorException;
import com.fiap.sociallearn.model.User;
import com.fiap.sociallearn.request.UserLearningContentRequest;
import com.fiap.sociallearn.request.UserRequest;
import com.fiap.sociallearn.response.UserLearningContentResponse;
import com.fiap.sociallearn.response.UserResponse;
import com.fiap.sociallearn.service.UserLearningContentService;
import com.fiap.sociallearn.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api("User")
@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  UserService userService;

  @Autowired
  UserLearningContentService userLearningContentService;

  @ApiOperation(value = "Register user")
  @PostMapping("/register")
  public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
    try {
      var savedUser = userService.save(userRequest.toEntity());
      return ResponseEntity.ok().body(savedUser.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(e.getStatus()).header(e.getMessage()).build();
    }
  }

  @ApiOperation(value = "Find user by id")
  @GetMapping("/{userId}")
  public ResponseEntity<UserResponse> findById(@PathVariable final String userId) {
    try {
      var user = userService.findById(userId);
      return ResponseEntity.ok().body(user.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(e.getStatus()).header(e.getMessage()).build();
    }
  }

  @ApiOperation(value = "Find all users")
  @GetMapping("/all")
  public ResponseEntity<List<UserResponse>> findAll() {
    List<User> userList = userService.findAll();
    List<UserResponse> userResponseList =
        userList.stream().map(user -> user.toResponse()).collect(Collectors.toList());
    return ResponseEntity.ok().body(userResponseList);
  }

  @ApiOperation(value = "Update user")
  @PutMapping("/update/{userId}")
  public ResponseEntity<UserResponse> update(@PathVariable String userId,
      @RequestBody UserRequest userRequest) {
    try {
      var user = userService.update(userId, userRequest.toEntity());
      return ResponseEntity.ok().body(user.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(e.getStatus()).header(e.getMessage()).build();
    }
  }

  @ApiOperation(value = "Associate user to learning content")
  @PostMapping("/associate/learningContent")
  public ResponseEntity<UserLearningContentResponse> associateLearningContent(
      @RequestBody UserLearningContentRequest userLearningContentRequest) {
    try {
      var savedUserLearningContent =
          userLearningContentService.save(userLearningContentRequest.toEntity());
      return ResponseEntity.ok().body(savedUserLearningContent.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(e.getStatus()).header(e.getMessage()).build();
    }
  }

  @ApiOperation(value = "Inactivate user")
  @PutMapping("/inactivate/{userId}")
  public ResponseEntity<UserResponse> inactivate(@PathVariable final String userId) {
    try {
      var user = userService.inactive(userId);
      return ResponseEntity.ok().body(user.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.notFound().build();
    }
  }

  @ApiOperation(value = "Delete user")
  @DeleteMapping("/delete/{userId}")
  public ResponseEntity<String> delete(@PathVariable final String userId) {
    try {
      userService.deleteById(userId);
      return ResponseEntity.ok().body("User deleted");
    } catch (EmptyResultDataAccessException e) {
      e.printStackTrace();
      return ResponseEntity.notFound().build();
    }
  }
}