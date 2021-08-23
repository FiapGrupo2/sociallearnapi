package com.fiap.sociallearn.controller;

import com.fiap.sociallearn.exceptions.ApiErrorException;
import com.fiap.sociallearn.model.ContentArea;
import com.fiap.sociallearn.request.ContentAreaRequest;
import com.fiap.sociallearn.response.ContentAreaResponse;
import com.fiap.sociallearn.service.ContentAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api("ContentArea")
@RestController
@RequestMapping("/contentArea")
public class ContentAreaController {
  @Autowired
  ContentAreaService contentAreaService;

  @ApiOperation(value = "Register content area")
  @PostMapping("/register")
  public ResponseEntity<ContentAreaResponse> schedule(
      @RequestBody ContentAreaRequest contentAreaRequest) {
    try {
      var savedContentArea = contentAreaService.save(contentAreaRequest.toEntity());
      return ResponseEntity.ok().body(savedContentArea.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(e.getStatus()).header(e.getMessage()).build();
    }
  }

  @ApiOperation(value = "Search content area by id")
  @GetMapping("/{contentAreaId}")
  public ResponseEntity<ContentAreaResponse> findById(@PathVariable final Long contentAreaId) {
    try {
      var contentArea = contentAreaService.findById(contentAreaId);
      return ResponseEntity.ok().body(contentArea.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(e.getStatus()).header(e.getMessage()).build();
    }
  }

  @ApiOperation(value = "Search all content area")
  @GetMapping("/all")
  public ResponseEntity<List<ContentAreaResponse>> findAll() {
    List<ContentArea> contentAreaList = contentAreaService.findAll();
    List<ContentAreaResponse> contentAreaResponseList = contentAreaList.stream()
        .map(contentArea -> contentArea.toResponse())
        .collect(Collectors.toList());
    return ResponseEntity.ok().body(contentAreaResponseList);
  }

  @ApiOperation(value = "Update content area")
  @PutMapping("/update/{contentAreaId}")
  public ResponseEntity<ContentAreaResponse> update(@PathVariable Long contentAreaId,
      @RequestBody ContentAreaRequest contentAreaRequest) {
    try {
      var contentArea = contentAreaService.update(contentAreaId, contentAreaRequest.toEntity());
      return ResponseEntity.ok().body(contentArea.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(e.getStatus()).header(e.getMessage()).build();
    }
  }

  @ApiOperation(value = "Inactivate content area")
  @PutMapping("/inactivate/{contentAreaId}")
  public ResponseEntity<ContentAreaResponse> inactivate(@PathVariable Long contentAreaId) {
    try {
      var contentArea = contentAreaService.inactivate(contentAreaId);
      return ResponseEntity.ok().body(contentArea.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(e.getStatus()).build();
    }
  }

  @ApiOperation(value = "Delete content area")
  @DeleteMapping("/delete/{contentAreaId}")
  public ResponseEntity<String> delete(@PathVariable Long contentAreaId) {
    try {
      contentAreaService.deleteById(contentAreaId);
      return ResponseEntity.ok().body("Content area deleted");
    } catch (EmptyResultDataAccessException e) {
      e.printStackTrace();
      return ResponseEntity.notFound().build();
    }
  }
}