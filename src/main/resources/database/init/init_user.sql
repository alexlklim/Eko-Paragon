INSERT INTO users (firstname, lastname, email, password, is_active, created, updated)
VALUES ('Alex', 'Klim',
        'admin@gmail.com',
        '$2a$10$G7/RXIL6FTjldvXU60lM9OkZNH/DeniXHbskTUyQ7lVpU/C..Aeb2',
        TRUE, NOW(), NOW());


SET @userId = LAST_INSERT_ID();

INSERT INTO user_roles (user_id, role)
VALUES (@userId, 'ADMIN'),
       (@userId, 'MAN'),
       (@userId, 'USER');
