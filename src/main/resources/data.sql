INSERT INTO file_store
(file_name, file_path, file_size, file_type, file_status, file_category, created_at, updated_at)
VALUES
    ('example-image.jpg', '/files/images/example-image.jpg', 1024, 'image/jpeg', 'ACTIVE', 'IMAGE', now(), now())
    ON CONFLICT (file_name) DO NOTHING;

INSERT INTO file_store
(file_name, file_path, file_size, file_type, file_status, file_category, created_at, updated_at)
VALUES
    ('example-photo.png', '/files/images/example-photo.png', 2048, 'image/png', 'ACTIVE', 'IMAGE', now(), now())
    ON CONFLICT (file_name) DO NOTHING;

INSERT INTO file_store
(file_name, file_path, file_size, file_type, file_status, file_category, created_at, updated_at)
VALUES
    ('example-slide.pptx', '/files/docs/example-slide.pptx', 4096, 'application/vnd.openxmlformats-officedocument.presentationml.presentation', 'ACTIVE', 'DOCUMENT', now(), now())
    ON CONFLICT (file_name) DO NOTHING;

INSERT INTO file_store
(file_name, file_path, file_size, file_type, file_status, file_category, created_at, updated_at)
VALUES
    ('example-music.wav', '/files/audio/example-music.wav', 1024000, 'audio/wav', 'ACTIVE', 'AUDIO', now(), now())
    ON CONFLICT (file_name) DO NOTHING;

INSERT INTO file_store
(file_name, file_path, file_size, file_type, file_status, file_category, created_at, updated_at)
VALUES
    ('example-document.pdf', '/files/docs/example-document.pdf', 512000, 'application/pdf', 'ACTIVE', 'DOCUMENT', now(), now())
    ON CONFLICT (file_name) DO NOTHING;

INSERT INTO file_store
(file_name, file_path, file_size, file_type, file_status, file_category, created_at, updated_at)
VALUES
    ('example-audio.mp3', '/files/audio/example-audio.mp3', 2048000, 'audio/mpeg', 'ACTIVE', 'AUDIO', now(), now())
    ON CONFLICT (file_name) DO NOTHING;

INSERT INTO file_store
(file_name, file_path, file_size, file_type, file_status, file_category, created_at, updated_at)
VALUES
    ('example-spreadsheet.xlsx', '/files/docs/example-spreadsheet.xlsx', 307200, 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', 'ACTIVE', 'DOCUMENT', now(), now())
    ON CONFLICT (file_name) DO NOTHING;

INSERT INTO file_store
(file_name, file_path, file_size, file_type, file_status, file_category, created_at, updated_at)
VALUES
    ('example-presentation.pptx', '/files/docs/example-presentation.pptx', 409600, 'application/vnd.openxmlformats-officedocument.presentationml.presentation', 'ACTIVE', 'DOCUMENT', now(), now())
    ON CONFLICT (file_name) DO NOTHING;

INSERT INTO file_store
(file_name, file_path, file_size, file_type, file_status, file_category, created_at, updated_at)
VALUES
    ('example-image2.jpg', '/files/images/example-image2.jpg', 3072, 'image/jpeg', 'ACTIVE', 'IMAGE', now(), now())
    ON CONFLICT (file_name) DO NOTHING;
