package com.streaming.streaming.infrastructure.out.persistence.repository;

import com.streaming.streaming.infrastructure.out.persistence.entity.CategoryEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryCrudRepository extends ReactiveCrudRepository<CategoryEntity, UUID> {
}
