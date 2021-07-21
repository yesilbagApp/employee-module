package com.employee.service;

import com.employee.domain.DayOff;

public interface DayOffQueryService {

    DayOff getDayOffByEmployeeId(Long id);
}
