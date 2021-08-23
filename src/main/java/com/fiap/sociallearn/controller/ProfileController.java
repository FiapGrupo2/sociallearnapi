package com.fiap.sociallearn.controller;

import com.fiap.sociallearn.exceptions.ApiErrorException;
import com.fiap.sociallearn.model.Profile;
import com.fiap.sociallearn.request.ProfileRequest;
import com.fiap.sociallearn.response.ProfileResponse;
import com.fiap.sociallearn.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api("Profile")
@RestController
@RequestMapping("/profile")
public class ProfileController {
  @Autowired
  ProfileService profileService;

  @ApiOperation(value = "Register profile")
  @PostMapping("/register")
  public ResponseEntity<ProfileResponse> schedule(@RequestBody ProfileRequest profileRequest) {
    try {
      var savedProfile = profileService.save(profileRequest.toEntity());
      return ResponseEntity.ok().body(savedProfile.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(e.getStatus()).header(e.getMessage()).build();
    }
  }

  @ApiOperation(value = "Search profile by id")
  @GetMapping("/{profileId}")
  public ResponseEntity<ProfileResponse> findById(@PathVariable final Long profileId) {
    try {
      var profile = profileService.findById(profileId);
      return ResponseEntity.ok().body(profile.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(e.getStatus()).header(e.getMessage()).build();
    }
  }

  @ApiOperation(value = "Search all profile")
  @GetMapping("/all")
  public ResponseEntity<List<ProfileResponse>> findAll() {
    List<Profile> profileList = profileService.findAll();
    List<ProfileResponse> profileResponseList =
        profileList.stream().map(profile -> profile.toResponse()).collect(Collectors.toList());
    return ResponseEntity.ok().body(profileResponseList);
  }

  @ApiOperation(value = "Update profile")
  @PutMapping("/update/{profileId}")
  public ResponseEntity<ProfileResponse> update(@PathVariable Long profileId,
      @RequestBody ProfileRequest profileRequest) {
    try {
      var profile = profileService.update(profileId, profileRequest.toEntity());
      return ResponseEntity.ok().body(profile.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(e.getStatus()).header(e.getMessage()).build();
    }
  }

  @ApiOperation(value = "Inactivate profile")
  @PutMapping("/inactivate/{profileId}")
  public ResponseEntity<ProfileResponse> inactivate(@PathVariable Long profileId) {
    try {
      var profile = profileService.inactivate(profileId);
      return ResponseEntity.ok().body(profile.toResponse());
    } catch (ApiErrorException e) {
      e.printStackTrace();
      return ResponseEntity.status(e.getStatus()).build();
    }
  }

  @ApiOperation(value = "Delete profile")
  @DeleteMapping("/delete/{profileId}")
  public ResponseEntity<String> delete(@PathVariable Long profileId) {
    try {
      profileService.deleteById(profileId);
      return ResponseEntity.ok().body("Profile deleted");
    } catch (EmptyResultDataAccessException e) {
      e.printStackTrace();
      return ResponseEntity.notFound().build();
    }
  }
}