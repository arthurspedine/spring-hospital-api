CREATE TABLE medic_appointments
(
    appointment_id BIGINT NOT NULL,
    medic_id       BIGINT NOT NULL,
    PRIMARY KEY (appointment_id, medic_id),
    CONSTRAINT fk_medic_appointment_appointment FOREIGN KEY (appointment_id) REFERENCES appointments (id),
    CONSTRAINT fk_medic_appointment_medic FOREIGN KEY (medic_id) REFERENCES medics (id)
);