CREATE TABLE users
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    login    VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(100) NOT NULL,
        PRIMARY KEY (id)
);