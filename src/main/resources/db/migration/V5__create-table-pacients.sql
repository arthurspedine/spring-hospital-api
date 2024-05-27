CREATE TABLE pacients
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL,
    cpf        VARCHAR(14)  NOT NULL UNIQUE,
    birth_date DATE         NOT NULL,
    user_id BIGINT UNIQUE,
    PRIMARY KEY (id),
    CONSTRAINT fk_pacient_user FOREIGN KEY (user_id) REFERENCES users (id)
);