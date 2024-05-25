package com.hospital_api.domain.employee;

public record EmployeeDetail(
        String name,
        String cpf,
        EmployeeType type,
        String login
) {
    public EmployeeDetail(Employee e) {
        this(e.getName(), e.getCpf(), e.getType(), e.getUser().getLogin());
    }
}
