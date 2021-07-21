package com.employee.service;

import com.employee.domain.DayOffRequest;
import liquibase.pro.packaged.T;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DayOffRequestQueryService {

    ResponseEntity<T> getAllRequestToManagerAsDTO(Long id);

    DayOffRequest getDayOffRequest(Long id);

    List<DayOffRequest> getDayOffRequestList(Long id);

    ResponseEntity<T> getAllPendingRequestToManager(Long id);
}
