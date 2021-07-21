package com.employee.repository;

import com.employee.domain.DayOffRequest;
import com.employee.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayoffRequestRepository extends JpaRepository<DayOffRequest, Long> {

    List<DayOffRequest> findAllByApprovedEmployeeId(Long id);

    List<DayOffRequest> findAllByStatusAndApprovedEmployeeId(Status status, Long id);

    List<DayOffRequest> findAllByEmployeeId(Long id);
}
