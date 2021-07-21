package com.employee.service;

import com.employee.api.request.CreateDayOffRequest;
import liquibase.pro.packaged.T;
import org.springframework.http.ResponseEntity;

public interface DayOffRequestCommandService {
    ResponseEntity<T> createDayOffRequest(CreateDayOffRequest createDayOffRequest);

    ResponseEntity<T> approveDayOffRequest(Long dayOffRequestId);

    ResponseEntity<T> rejectedDayOffRequest(Long dayOffRequestId);
}
