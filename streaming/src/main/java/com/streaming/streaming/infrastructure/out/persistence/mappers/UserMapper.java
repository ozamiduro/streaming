package com.streaming.streaming.infrastructure.out.persistence.mappers;

import com.streaming.streaming.domain.model.User;
import com.streaming.streaming.infrastructure.out.persistence.entity.UserEntity;

public class UserMapper {
    public static User toDomain(UserEntity entity) {
        return new User(entity.id(), entity.username(), entity.email(), entity.passwordHash(), entity.createdAt(), entity.updatedAt());
    }

    public static UserEntity toEntity(User domain) {
        return new UserEntity(domain.id(), domain.username(), domain.email(), domain.passwordHash(), domain.createdAt(), domain.updatedAt());
    }
}
