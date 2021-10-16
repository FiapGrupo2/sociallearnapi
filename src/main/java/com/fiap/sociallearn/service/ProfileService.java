package com.fiap.sociallearn.service;

import com.fiap.sociallearn.exceptions.ApiErrorException;
import com.fiap.sociallearn.model.Profile;
import com.fiap.sociallearn.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
  @Autowired
  ProfileRepository profileRepository;

  public Profile save(Profile profile) {
    return profileRepository.save(profile);
  }

  public void deleteById(String id) {
    profileRepository.deleteById(id);
  }

  public Profile findById(String id) throws ApiErrorException {
    Optional<Profile> optionalProfile = profileRepository.findById(id);
    return optionalProfile.orElseThrow(
        () -> new ApiErrorException(HttpStatus.NOT_FOUND, "The informed profile doesn't exists"));
  }

  public List<Profile> findAll() {
    return (List<Profile>) profileRepository.findAll();
  }

  public Profile update(String profileId, Profile updatedProfile) throws ApiErrorException {
    var profile = updateSavedProfile(profileId, updatedProfile);
    return profileRepository.save(profile);
  }

  private Profile updateSavedProfile(final String profileId, final Profile updatedProfile)
      throws ApiErrorException {
    var savedProfile = findById(profileId);
    savedProfile.setName(updatedProfile.getName());
    savedProfile.setActive(updatedProfile.isActive());
    savedProfile.setLastModifiedDate(Date.from(Instant.now()));
    return savedProfile;
  }

  public Profile inactivate(String profileId) throws ApiErrorException {
    var savedProfile = findById(profileId);
    savedProfile.setActive(false);
    return profileRepository.save(savedProfile);
  }
}
