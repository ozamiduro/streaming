package com.streaming.streaming.infrastructure.out.persistence.repository;

import com.streaming.streaming.infrastructure.out.persistence.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserCrudRepository extends ReactiveCrudRepository<UserEntity, UUID> {
}
