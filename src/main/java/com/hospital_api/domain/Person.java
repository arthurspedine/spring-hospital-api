package com.hospital_api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Person {

    private String name;

    @Column(length = 14, nullable = false)
    private String cpf;

}
