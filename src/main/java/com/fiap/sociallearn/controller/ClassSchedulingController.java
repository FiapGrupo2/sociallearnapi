package com.fiap.sociallearn.controller;

import com.fiap.sociallearn.exceptions.ApiErrorException;
import com.fiap.sociallearn.model.ClassScheduling;
import com.fiap.sociallearn.request.ClassSchedulingRequest;
import com.fiap.sociallearn.response.ClassSchedulingResponse;
import com.fiap.sociallearn.service.ClassSchedulingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api("Class")
@RestController
@RequestMapping("/class")
public class ClassSchedulingController {
  @Autowired
  ClassSchedulingService classSchedulingService;

  @ApiOperation(value = "Register class scheduling")
  @PostMapping("/schedule")
  public ResponseEntity<ClassSchedulingResponse> schedule(@RequestBody ClassSchedulingRequest classSchedulingRequest) {
    try {
      var savedClassScheduling = classSchedulingService.save(classSchedulingRequest.toEntity());
      return ResponseEntity.ok().body(savedClassScheduling.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header(e.getMessage()).build();
    }
  }

  @ApiOperation(value = "Search class scheduling by id")
  @GetMapping("/{classSchedulingId}")
  public ResponseEntity<ClassSchedulingResponse> findById(@PathVariable final String classSchedulingId) {
    try {
      var classScheduling = classSchedulingService.findById(classSchedulingId);
      return ResponseEntity.ok().body(classScheduling.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header(e.getMessage()).build();
    }
  }

  @ApiOperation(value = "Search all class scheduling")
  @GetMapping("/all")
  public ResponseEntity<List<ClassSchedulingResponse>> findAll() {
    List<ClassScheduling> classSchedulingList = classSchedulingService.findAll();
    List<ClassSchedulingResponse> classSchedulingResponseList = classSchedulingList.stream()
        .map(classScheduling -> classScheduling.toResponse())
        .collect(Collectors.toList());
    return ResponseEntity.ok().body(classSchedulingResponseList);
  }

  @ApiOperation(value = "Search all class scheduling by userId")
  @GetMapping("/all/user/{userId}")
  public ResponseEntity<List<ClassSchedulingResponse>> findAllByUserId(@PathVariable final String userId) {
    List<ClassScheduling> classSchedulingList = classSchedulingService.findAllByUserId(userId);
    List<ClassSchedulingResponse> classSchedulingResponseList = classSchedulingList.stream()
        .map(classScheduling -> classScheduling.toResponse())
        .collect(Collectors.toList());
    return ResponseEntity.ok().body(classSchedulingResponseList);
  }

  @ApiOperation(value = "Update class scheduling")
  @PutMapping("/update/{classSchedulingId}")
  public ResponseEntity<ClassSchedulingResponse> update(@PathVariable String classSchedulingId,@RequestBody ClassSchedulingRequest classSchedulingRequest) {
    try {
      var classScheduling =
          classSchedulingService.update(classSchedulingId, classSchedulingRequest.toEntity());
      return ResponseEntity.ok().body(classScheduling.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header(e.getMessage()).build();
    }
  }

  @ApiOperation(value = "Inactivate class scheduling")
  @PutMapping("/inactivate/{classSchedulingId}")
  public ResponseEntity<ClassSchedulingResponse> inactivate(@PathVariable String classSchedulingId) {
    try {
      var classScheduling = classSchedulingService.inactivate(classSchedulingId);
      return ResponseEntity.ok().body(classScheduling.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @ApiOperation(value = "Delete class scheduling")
  @DeleteMapping("/delete/{classSchedulingId}")
  public ResponseEntity<String> delete(@PathVariable String classSchedulingId) {
    try {
      classSchedulingService.deleteById(classSchedulingId);
      return ResponseEntity.ok().body("Class scheduling deleted");
    } catch (EmptyResultDataAccessException e) {
      e.printStackTrace();
      return ResponseEntity.notFound().build();
    }
  }
}