CREATE TABLE appointments
(
    id         BIGINT        NOT NULL AUTO_INCREMENT,
    pacient_id BIGINT        NOT NULL,
    date_time  DATETIME      NOT NULL,
    reason     VARCHAR(1000) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_pacient FOREIGN KEY (pacient_id) REFERENCES pacients (id)
);