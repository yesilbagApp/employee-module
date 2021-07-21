package com.employee.service.impl;

import com.employee.api.response.ResponseHandler;
import com.employee.domain.DayOffRequest;
import com.employee.enums.Status;
import com.employee.mapper.DayoffRequestMapper;
import com.employee.repository.DayoffRequestRepository;
import com.employee.service.DayOffRequestQueryService;
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
public class DayOffRequestQueryServiceImpl implements DayOffRequestQueryService {

    private final DayoffRequestRepository dayoffRequestRepository;
    private final DayoffRequestMapper dayoffRequestMapper;
    private final LocalizationService localizationService;

    @Override
    public ResponseEntity<T> getAllRequestToManagerAsDTO(Long id) {
        List<DayOffRequest> dayOffRequestList = this.dayoffRequestRepository.findAllByApprovedEmployeeId(id);
        return ResponseHandler.generateResponse(this.localizationService.getLocalizationMessage("retrieved.successfully"), HttpStatus.OK, dayoffRequestMapper.toDTOList(dayOffRequestList));
    }

    @Override
    public DayOffRequest getDayOffRequest(Long id) {
        return this.dayoffRequestRepository.findById(id).orElse(null);
    }

    @Override
    public List<DayOffRequest> getDayOffRequestList(Long id) {
        return this.dayoffRequestRepository.findAllByEmployeeId(id);
    }

    @Override
    public ResponseEntity<T> getAllPendingRequestToManager(Long id) {
        List<DayOffRequest> dayOffRequestList = this.dayoffRequestRepository.findAllByStatusAndApprovedEmployeeId(Status.PENDING, id);
        return ResponseHandler.generateResponse(this.localizationService.getLocalizationMessage("retrieved.successfully"), HttpStatus.OK, dayoffRequestMapper.toDTOList(dayOffRequestList));
    }
}
