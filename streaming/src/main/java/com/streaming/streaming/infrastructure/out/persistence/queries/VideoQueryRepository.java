package com.streaming.streaming.infrastructure.out.persistence.queries;

import com.streaming.streaming.infrastructure.out.persistence.entity.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class VideoQueryRepository {
    private final DatabaseClient databaseClient;

    public Flux<CategoryEntity> findCategoriesByVideoId(
            UUID videoId) {

        return databaseClient
                .sql("""
                    SELECT c.*
                    FROM categories c
                    JOIN video_categories vc
                      ON vc.category_id = c.id
                    WHERE vc.video_id = :videoId
                """)
                .bind("videoId", videoId)
                .map((row, meta) ->
                        new CategoryEntity(
                                row.get("id", UUID.class),
                                row.get("name", String.class)
                        ))
                .all();
    }
}
