package com.streaming.streaming.infrastructure.out.persistence.mappers;

import com.streaming.streaming.domain.model.Category;
import com.streaming.streaming.infrastructure.out.persistence.entity.CategoryEntity;

public class CategoryMapper {
    public static Category toDomain(CategoryEntity entity) {
        return new Category(entity.id(), entity.name());
    }

    public static CategoryEntity toEntity(Category domain) {
        return new CategoryEntity(domain.id(), domain.name());
    }
}
