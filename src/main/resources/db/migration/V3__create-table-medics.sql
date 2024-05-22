CREATE TABLE medics
(
    id        BIGINT PRIMARY KEY,
    crm       VARCHAR(255) NOT NULL UNIQUE,
    specialty VARCHAR(50)  NOT NULL,
    CONSTRAINT fk_medic_employee FOREIGN KEY (id) REFERENCES employees (id)
);