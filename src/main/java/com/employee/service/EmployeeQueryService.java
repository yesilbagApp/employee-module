package com.employee.service;

import com.employee.domain.Employee;
import liquibase.pro.packaged.T;
import org.springframework.http.ResponseEntity;

public interface EmployeeQueryService {


    ResponseEntity<T> getEmployeeByIdAsDTO(Long id);

    Employee getEmployeeById(Long id);

    ResponseEntity<T> getEmployeeByNameAsDTO(String name);

    ResponseEntity<T> getAllEmployees();
}
