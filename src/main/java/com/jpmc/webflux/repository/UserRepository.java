package com.jpmc.webflux.repository;

import com.jpmc.webflux.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.util.ReactiveWrapperConverters;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {

}
