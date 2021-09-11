package com.fiap.sociallearn.service;

import com.fiap.sociallearn.exceptions.ApiErrorException;
import com.fiap.sociallearn.model.Profile;
import com.fiap.sociallearn.model.User;
import com.fiap.sociallearn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  public User save(User user) throws ApiErrorException {
    return userRepository.save(user);
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

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      User currentUser = userRepository.findByEmail(email);
        UserDetails user = new org.springframework.security.core.userdetails.User(email, currentUser.getPassword()
        , true, true, true, true, Collections.emptyList());
        return user;
  }
}
