package com.fiap.sociallearn.repository;

import com.fiap.sociallearn.model.ContentArea;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentAreaRepository extends MongoRepository<ContentArea, String> {
}
