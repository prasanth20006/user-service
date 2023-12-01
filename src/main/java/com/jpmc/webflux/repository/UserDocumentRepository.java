package com.jpmc.webflux.repository;

import com.jpmc.webflux.entity.UserDocumentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDocumentRepository extends ReactiveCrudRepository<UserDocumentEntity,Integer> {
}
