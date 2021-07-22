package com.employee.service.impl;

import com.employee.api.response.ResponseHandler;
import com.employee.domain.Employee;
import com.employee.mapper.EmployeeMapper;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeQueryService;
import liquibase.pro.packaged.T;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeQueryServiceImpl implements EmployeeQueryService {

    private final EmployeeRepository employeeRepository;
    private final LocalizationService localizationService;

    @Override
    public ResponseEntity<T> getEmployeeByIdAsDTO(Long id) {
        Employee employee = this.getEmployeeById(id);
        if (employee == null) {
            return ResponseHandler.generateResponse(this.localizationService.getLocalizationMessage("employee.not.found"), HttpStatus.NOT_FOUND, null);
        }
        return ResponseHandler.generateResponse(this.localizationService.getLocalizationMessage("retrieved.successfully"), HttpStatus.OK, EmployeeMapper.INSTANCE.toDTO(employee));
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<T> getEmployeeByNameAsDTO(String name) {
        Employee employee = this.employeeRepository.findByName(name);
        if (employee == null) {
            return ResponseHandler.generateResponse(this.localizationService.getLocalizationMessage("employee.not.found"), HttpStatus.NOT_FOUND, null);
        }
        return ResponseHandler.generateResponse(this.localizationService.getLocalizationMessage("retrieved.successfully"), HttpStatus.OK, EmployeeMapper.INSTANCE.toDTO(employee));
    }

    @Override
    public ResponseEntity<T> getAllEmployees() {
        List<Employee> employeeList = this.employeeRepository.findAll();
        return ResponseHandler.generateResponse(this.localizationService.getLocalizationMessage("retrieved.successfully"), HttpStatus.OK, EmployeeMapper.INSTANCE.toDTOList(employeeList));
    }
}
