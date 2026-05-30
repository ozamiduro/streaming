package com.streaming.streaming.infrastructure.out.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("categories")
public record CategoryEntity(
        @Id UUID id,
        String name
) {
}
