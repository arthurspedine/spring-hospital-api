CREATE TABLE receptionists
(
    id    BIGINT PRIMARY KEY,
    shift VARCHAR(50) NOT NULL,
    CONSTRAINT fk_receptionist_employee FOREIGN KEY (id) REFERENCES employees (id)
);