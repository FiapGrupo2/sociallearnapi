package com.fiap.sociallearn.service;

import com.fiap.sociallearn.exceptions.ApiErrorException;
import com.fiap.sociallearn.model.ContentArea;
import com.fiap.sociallearn.repository.ContentAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContentAreaService {
  @Autowired
  ContentAreaRepository contentAreaRepository;

  public ContentArea save(ContentArea contentArea) {
    return contentAreaRepository.save(contentArea);
  }

  public void deleteById(Long id) {
    contentAreaRepository.deleteById(id);
  }

  public ContentArea findById(Long id) throws ApiErrorException {
    Optional<ContentArea> optionalContentArea = contentAreaRepository.findById(id);
    return optionalContentArea.orElseThrow(() -> new ApiErrorException(HttpStatus.NOT_FOUND,
        "The informed content area doesn't exists"));
  }

  public List<ContentArea> findAll() {
    return (List<ContentArea>) contentAreaRepository.findAll();
  }

  public ContentArea update(Long contentAreaId, ContentArea updatedContentArea)
      throws ApiErrorException {
    var contentArea = updateSavedContentArea(contentAreaId, updatedContentArea);
    return contentAreaRepository.save(contentArea);
  }

  private ContentArea updateSavedContentArea(final Long contentAreaId,
      final ContentArea updatedContentArea) throws ApiErrorException {
    var savedContentArea = findById(contentAreaId);
    savedContentArea.setName(updatedContentArea.getName());
    savedContentArea.setActive(updatedContentArea.isActive());
    savedContentArea.setLastModifiedDate(Date.from(Instant.now()));
    return savedContentArea;
  }

  public ContentArea inactivate(Long contentAreaId) throws ApiErrorException {
    var savedContentArea = findById(contentAreaId);
    savedContentArea.setActive(false);
    return contentAreaRepository.save(savedContentArea);
  }
}
