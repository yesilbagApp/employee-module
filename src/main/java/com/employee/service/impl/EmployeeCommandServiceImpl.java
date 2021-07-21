package com.employee.service.impl;

import com.employee.domain.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeCommandServiceImpl implements EmployeeCommandService {

    private final EmployeeRepository employeeRepository;

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = this.employeeRepository.findById(employeeId).get();
        employee.setDeleted(true);
        this.employeeRepository.save(employee);
    }
}
