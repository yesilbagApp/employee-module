package com.employee.api.controller;

import com.employee.api.constants.ApiEndpoints;
import com.employee.api.constants.ApiGroups;
import com.employee.api.request.CreateDayOffRequest;
import com.employee.service.DayOffRequestCommandService;
import com.employee.service.DayOffRequestQueryService;
import com.employee.service.EmployeeCommandService;
import com.employee.service.EmployeeQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liquibase.pro.packaged.T;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = ApiEndpoints.EMPLOYEEREST_API, produces = ApiEndpoints.RESPONSE_CONTENTTYPE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
@Api(value = ApiGroups.Employee.NAME)
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeCommandService employeeCommandService;
    private final EmployeeQueryService employeeQueryService;
    private final DayOffRequestCommandService dayOffRequestCommandService;
    private final DayOffRequestQueryService dayOffRequestQueryService;

    @PostMapping(value = "/approve/dayOffRequest/{dayOffRequestId}", consumes = {MediaType.ALL_VALUE})
    @ApiOperation(value = "Approved Day Off Request By Request Id", notes = "Approved Day Off Request By Request Id")
    public ResponseEntity<T> approveDayOffRequest(@PathVariable("dayOffRequestId") Long dayOffRequestId) {
        return this.dayOffRequestCommandService.approveDayOffRequest(dayOffRequestId);
    }

    @PostMapping(value = "/rejected/dayOffRequest/{dayOffRequestId}", consumes = {MediaType.ALL_VALUE})
    @ApiOperation(value = "Rejected Day Off Request By Request Id", notes = "Rejected Day Off Request By Request Id")
    public ResponseEntity<T> rejectedDayOffRequest(@PathVariable("dayOffRequestId") Long dayOffRequestId) {
        return this.dayOffRequestCommandService.rejectedDayOffRequest(dayOffRequestId);
    }

    @PostMapping("/dayOffRequest")
    @ApiOperation(value = "Create Day Off Request", notes = "Create Day Off Request")
    public ResponseEntity<T> createDayOffRequest(@RequestBody CreateDayOffRequest createDayOffRequest) {
        return this.dayOffRequestCommandService.createDayOffRequest(createDayOffRequest);
    }

    @GetMapping(value = "/getEmployeeById/{employeeId}", consumes = {MediaType.ALL_VALUE})
    @ApiOperation(value = "Get Employee By Employee Id", notes = "Get Employee By Employee Id")
    public ResponseEntity<T> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        return this.employeeQueryService.getEmployeeByIdAsDTO(employeeId);
    }

    @GetMapping(value = "/getAllRequestToManager/{managerId}", consumes = {MediaType.ALL_VALUE})
    @ApiOperation(value = "Get All Request To Manager By Manager Id", notes = "Get All Request To Manager By Manager Id")
    public ResponseEntity<T> getAllRequestToManager(@PathVariable("managerId") Long managerId) {
        return this.dayOffRequestQueryService.getAllRequestToManagerAsDTO(managerId);
    }

    @GetMapping(value = "/getAllPendingRequestToManager/{managerId}", consumes = {MediaType.ALL_VALUE})
    @ApiOperation(value = "Get All Pending Request To Manager By Manager Id", notes = "Get All Pending Request To Manager By Manager Id")
    public ResponseEntity<T> getAllPendingRequestToManager(@PathVariable("managerId") Long managerId) {
        return this.dayOffRequestQueryService.getAllPendingRequestToManager(managerId);
    }

    @GetMapping(value = "/getAllEmployees", consumes = {MediaType.ALL_VALUE})
    @ApiOperation(value = "Get All Employees", notes = "Get All Employees")
    public ResponseEntity<T> getAllEmployees() {
        return this.employeeQueryService.getAllEmployees();
    }

    @DeleteMapping(value = "/delete/{employeeId}", consumes = {MediaType.ALL_VALUE})
    @ApiOperation(value = "Delete Employee By Employee Id", notes = "Delete Employee By Employee Id")
    public void deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        this.employeeCommandService.deleteEmployee(employeeId);
    }

    @GetMapping(value = "/getEmployeeByName/{name}", consumes = {MediaType.ALL_VALUE})
    @ApiOperation(value = "Get Employee By Employee Name", notes = "Get Employee By Employee Name")
    public ResponseEntity<T> getEmployeeByName(@PathVariable("name") String name) {
        return this.employeeQueryService.getEmployeeByNameAsDTO(name);
    }
}
