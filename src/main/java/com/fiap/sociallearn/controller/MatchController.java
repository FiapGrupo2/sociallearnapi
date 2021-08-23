package com.fiap.sociallearn.controller;

import com.fiap.sociallearn.model.User;
import com.fiap.sociallearn.service.MatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("Match")
@RestController
@RequestMapping("/match")
public class MatchController {
  @Autowired
  MatchService matchService;

  @ApiOperation(value = "Find teachers by learning content")
  @GetMapping("/teachers/learningContent/{learningContentId}")
  public ResponseEntity<List<User>> findTeachersByLearningContent(Long learningContentId) {
    List<User> teachers = matchService.findAllTeachersByLearningContent(learningContentId);
    return ResponseEntity.ok().body(teachers);
  }

  @ApiOperation(value = "Find students by learning content")
  @GetMapping("/students/learningContent/{learningContentId}")
  public ResponseEntity<List<User>> findStudentsByLearningContent(Long learningContentId) {
    List<User> students = matchService.findAllStudentsByLearningContent(learningContentId);
    return ResponseEntity.ok().body(students);
  }
}
