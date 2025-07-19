package com.neoage.controller;

import com.neoage.dto.EmployeeDto;
import com.neoage.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping
    ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(employeeDto));
    }

    @PutMapping("/{id}")
    ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long id) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeDto, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    ResponseEntity<EmployeeDto> deleteAll() {
        employeeService.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
