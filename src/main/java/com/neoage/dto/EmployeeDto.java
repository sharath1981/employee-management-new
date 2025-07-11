package com.neoage.dto;

import lombok.Builder;

@Builder
public record EmployeeDto(long id, String name, int salary) {
}
