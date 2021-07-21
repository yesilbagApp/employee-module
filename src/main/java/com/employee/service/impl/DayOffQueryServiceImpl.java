package com.employee.service.impl;

import com.employee.domain.DayOff;
import com.employee.repository.DayOffRepository;
import com.employee.service.DayOffQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DayOffQueryServiceImpl implements DayOffQueryService {

    private final DayOffRepository dayOffRepository;

    @Override
    public DayOff getDayOffByEmployeeId(Long id) {
        return this.dayOffRepository.findByEmployeeId(id);
    }

}
