package com.fiap.sociallearn.service;

import com.fiap.sociallearn.exceptions.ApiErrorException;
import com.fiap.sociallearn.model.Profile;
import com.fiap.sociallearn.model.User;
import com.fiap.sociallearn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  ProfileService profileService;

  public User save(User user) throws ApiErrorException {
    valid(user);
    return userRepository.save(user);
  }

  private void valid(final User user) throws ApiErrorException {
    List<Profile> profiles = user.getProfiles();
    profiles.stream().forEach(profile -> profileService.findById(profile.getId()));
  }

  public User findById(String id) throws ApiErrorException {
    Optional<User> optionalUser = userRepository.findById(id);
    return optionalUser.orElseThrow(
        () -> new ApiErrorException(HttpStatus.NOT_FOUND, "The informed user doesn't exists"));
  }

  public List<User> findAll() {
    return (List<User>) userRepository.findAll();
  }

  public User update(String userId, User updatedUser) throws ApiErrorException {
    var user = updateSavedUser(userId, updatedUser);
    return userRepository.save(user);
  }

  private User updateSavedUser(final String userId, final User updatedUser) throws ApiErrorException {
    var savedUser = findById(userId);
    savedUser.setName(updatedUser.getName());
    savedUser.setEmail(updatedUser.getEmail());
    savedUser.setPassword(updatedUser.getPassword());
    savedUser.setGender(updatedUser.getGender());
    savedUser.setProfiles(updatedUser.getProfiles());
    savedUser.setActive(updatedUser.isActive());
    savedUser.setLastModifiedDate(Date.from(Instant.now()));
    return savedUser;
  }

  public User inactive(String userId) throws ApiErrorException {
    var savedUser = findById(userId);
    savedUser.setActive(false);
    return userRepository.save(savedUser);
  }

  public void deleteById(String id) {
    userRepository.deleteById(id);
  }
}
