package com.streaming.streaming.infrastructure.out.persistence.queries;

import com.streaming.streaming.domain.model.Category;
import com.streaming.streaming.infrastructure.out.persistence.entity.CategoryEntity;
import com.streaming.streaming.infrastructure.out.persistence.mappers.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class VideoCategoryQueryRepository {
    private final DatabaseClient databaseClient;

    public Flux<Category> findCategoriesByVideoId(UUID videoId) {
        String query = """
                SELECT
                    c.id,
                    c.name
                FROM categories c
                INNER JOIN video_categories vc
                    ON vc.category_id = c.id
                WHERE vc.video_id = :videoId
                """;
        return databaseClient.sql(query)
                .bind("videoId", videoId)
                .map((row, meta) -> CategoryMapper.toDomain(
                                new CategoryEntity(row.get("id", UUID.class), row.get("name", String.class)
                                )
                        )
                )
                .all();
    }
}
