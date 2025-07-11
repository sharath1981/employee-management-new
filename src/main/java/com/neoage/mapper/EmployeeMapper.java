package com.neoage.mapper;

import com.neoage.dto.EmployeeDto;
import com.neoage.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class EmployeeMapper {

    public EmployeeDto toDto(Employee employee) {
        return Optional.ofNullable(employee)
                .map(e -> new EmployeeDto(employee.getId(), employee.getName(), employee.getSalary()))
                .orElse(null);

    }

    public Employee toEntity(EmployeeDto employeeDto) {
        return Optional.ofNullable(employeeDto)
                .map(e -> Employee.builder().name(employeeDto.name()).salary(employeeDto.salary()).build())
                .orElse(null);
    }

    public Employee mergeToEntity(EmployeeDto employeeDto, Employee employee) {
        return Optional.ofNullable(employeeDto)
                .map(dto -> {
                    employee.setName(dto.name());
                    employee.setSalary(dto.salary());
                    return employee;
                }).orElse(null);
    }
}
