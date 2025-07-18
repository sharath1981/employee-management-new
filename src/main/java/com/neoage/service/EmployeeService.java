package com.neoage.service;

import com.neoage.dto.EmployeeDto;
import com.neoage.exception.EmployeeManagementException;
import com.neoage.mapper.EmployeeMapper;
import com.neoage.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto)
                .orElseThrow(() -> new EmployeeManagementException("Employee not found"));

    }

    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDto)
                .toList();
    }

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        return Optional.ofNullable(employeeDto)
                .map(employeeMapper::toEntity)
                .map(employeeRepository::save)
                .map(employeeMapper::toDto)
                .orElse(null);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.findById(id)
                .ifPresentOrElse(employeeRepository::delete, () -> {
                    throw new EmployeeManagementException("Employee not found");
                });
    }

    public void deleteAll() {
        employeeRepository.deleteAll();
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {
        return employeeRepository.findById(id)
                .map(employee -> employeeMapper.mergeToEntity(employeeDto, employee))
                .map(employeeRepository::save)
                .map(employeeMapper::toDto)
                .orElseThrow(() -> new EmployeeManagementException("Employee not found"));
    }
}
