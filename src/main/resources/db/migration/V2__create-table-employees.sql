CREATE TABLE employees
(
    id      BIGINT AUTO_INCREMENT,
    name    VARCHAR(255) NOT NULL,
    cpf     VARCHAR(14)  NOT NULL,
    type    VARCHAR(50)  NOT NULL,
    user_id BIGINT UNIQUE,
    PRIMARY KEY (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id)
);