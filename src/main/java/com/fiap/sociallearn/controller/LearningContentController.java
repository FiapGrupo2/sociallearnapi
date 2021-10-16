package com.fiap.sociallearn.controller;

import com.fiap.sociallearn.exceptions.ApiErrorException;
import com.fiap.sociallearn.model.LearningContent;
import com.fiap.sociallearn.request.LearningContentRequest;
import com.fiap.sociallearn.response.LearningContentResponse;
import com.fiap.sociallearn.service.LearningContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


//@Api("LearningContent")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/learningContents")
public class LearningContentController {
  @Autowired
  LearningContentService learningContentService;

  @ApiOperation(value = "Register learning content")
  @PostMapping("/register")
  public ResponseEntity<LearningContentResponse> schedule(
      @RequestBody LearningContentRequest learningContentRequest) {
    try {
      var savedLearningContent = learningContentService.save(learningContentRequest.toEntity());
      return ResponseEntity.ok().body(savedLearningContent.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header(e.getMessage()).build();
    }
  }

  @ApiOperation(value = "Search learning content by id")
  @GetMapping("/{learningContentId}")
  public ResponseEntity<LearningContentResponse> findById(
      @PathVariable final String learningContentId) {
    try {
      var learningContent = learningContentService.findById(learningContentId);
      return ResponseEntity.ok().body(learningContent.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(e.getStatus()).header(e.getMessage()).build();
    }
  }

  @ApiOperation(value = "Search all learning content")
  @GetMapping("/all/itens")
  public ResponseEntity<List<LearningContentResponse>> findAll() {
    List<LearningContent> learningContentList = learningContentService.findAll();
    List<LearningContentResponse> learningContentResponseList = learningContentList.stream()
        .map(learningContent -> learningContent.toResponse())
        .collect(Collectors.toList());
    return ResponseEntity.ok().body(learningContentResponseList);
  }

  @ApiOperation(value = "Update learning content")
  @PutMapping("/update/{learningContentId}")
  public ResponseEntity<LearningContentResponse> update(@PathVariable String learningContentId,
      @RequestBody LearningContentRequest learningContentRequest) {
    try {
      var learningContent =
          learningContentService.update(learningContentId, learningContentRequest.toEntity());
      return ResponseEntity.ok().body(learningContent.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header(e.getMessage()).build();
    }
  }

  @ApiOperation(value = "Inactivate learning content")
  @PutMapping("/inactivate/{learningContentId}")
  public ResponseEntity<LearningContentResponse> inactivate(@PathVariable String learningContentId) {
    try {
      var learningContent = learningContentService.inactivate(learningContentId);
      return ResponseEntity.ok().body(learningContent.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @ApiOperation(value = "Delete learning content")
  @DeleteMapping("/delete/{learningContentId}")
  public ResponseEntity<String> delete(@PathVariable String learningContentId) {
    try {
      learningContentService.deleteById(learningContentId);
      return ResponseEntity.ok().body("Learning content deleted");
    } catch (EmptyResultDataAccessException e) {
      e.printStackTrace();
      return ResponseEntity.notFound().build();
    }
  }
}