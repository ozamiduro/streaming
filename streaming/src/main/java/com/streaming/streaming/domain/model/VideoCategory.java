package com.streaming.streaming.domain.model;

import java.util.UUID;

public record VideoCategory(
        UUID videoId,
        UUID categoryId
) {
}
