-- 1. Create ENUM in safe way
DO $$
    BEGIN
        CREATE TYPE video_version_status AS ENUM (
            'PENDING',
            'PROCESSING',
            'READY',
            'FAILED',
            'DEPRECATED'
            );
    EXCEPTION
        WHEN duplicate_object THEN NULL;
    END $$;

-- 2. Users table
CREATE TABLE users
(
    id            UUID PRIMARY KEY,
    username      VARCHAR(50) UNIQUE NOT NULL,
    email         VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,

    created_at    TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at    TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- 3. Categories table
CREATE TABLE categories
(
    id   UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- 4. Videos table
CREATE TABLE videos
(
    id UUID PRIMARY KEY,

    user_id UUID NOT NULL
        REFERENCES users(id),

    title VARCHAR(255) NOT NULL,

    description TEXT,

    -- versión actualmente visible al público
    current_version_id UUID,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- 5. Pivot table (Video N <-> N Category)
CREATE TABLE video_categories
(
    video_id UUID NOT NULL
        REFERENCES videos(id)
            ON DELETE CASCADE,

    category_id UUID NOT NULL
        REFERENCES categories(id)
            ON DELETE CASCADE,

    PRIMARY KEY (video_id, category_id)
);

-- 6. Video versions
CREATE TABLE video_versions
(
    id UUID PRIMARY KEY,

    video_id UUID NOT NULL
        REFERENCES videos(id)
            ON DELETE CASCADE,

    version_number INT NOT NULL,

    status video_version_status NOT NULL
                                    DEFAULT 'PENDING',

    -- playlist principal generada por FFmpeg
    master_playlist_url TEXT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    UNIQUE(video_id, version_number)
);

-- 7. Video streams (1080p, 720p, 480p...)
CREATE TABLE video_streams
(
    id UUID PRIMARY KEY,

    video_version_id UUID NOT NULL
        REFERENCES video_versions(id)
            ON DELETE CASCADE,

    resolution VARCHAR(20) NOT NULL,

    bitrate INTEGER NOT NULL,

    playlist_url TEXT NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- 8. FK circular (videos -> current_version_id)
ALTER TABLE videos
    ADD CONSTRAINT fk_videos_current_version
        FOREIGN KEY (current_version_id)
            REFERENCES video_versions(id);

-- 9. Indexes
CREATE INDEX idx_videos_user_id
    ON videos(user_id);

CREATE INDEX idx_video_versions_video_id
    ON video_versions(video_id);

CREATE INDEX idx_video_streams_version_id
    ON video_streams(video_version_id);

CREATE INDEX idx_video_categories_video_id
    ON video_categories(video_id);

CREATE INDEX idx_video_categories_category_id
    ON video_categories(category_id);

-- 10. Trigger for updated_at
CREATE OR REPLACE FUNCTION update_updated_at_column()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_user_updated_at
    BEFORE UPDATE
    ON users
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();