
CREATE TABLE IF NOT EXISTS users
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    created           DATETIME,
    updated           DATETIME,

    is_active         BOOLEAN DEFAULT TRUE,


    firstname         VARCHAR(255),
    lastname          VARCHAR(255),
    email             VARCHAR(255) UNIQUE,
    password          VARCHAR(255),
    last_activity     DATETIME
);

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id           BIGINT,
    role             ENUM ('ADMIN', 'MAN', 'EMP', 'USER') DEFAULT 'USER'

);



CREATE TABLE IF NOT EXISTS tokens
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    created   DATETIME,
    expired   DATETIME,
    token      BINARY(16),

    user_id   BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id)
);



select * from employees;