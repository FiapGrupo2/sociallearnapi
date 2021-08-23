package com.fiap.sociallearn.repository;

import com.fiap.sociallearn.model.ContentArea;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentAreaRepository extends CrudRepository<ContentArea, Long> {
}
