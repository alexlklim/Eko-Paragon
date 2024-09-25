CREATE TABLE IF NOT EXISTS images
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    created           DATETIME,
    updated           DATETIME,
    created_by        BIGINT,
    deleted           BOOLEAN DEFAULT FALSE,

    is_public         BOOLEAN,
    name              VARCHAR(255),
    type              VARCHAR(255),
    byte_data         LONGBLOB

);
