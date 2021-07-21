package com.employee.repository;

import com.employee.domain.DayOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayOffRepository extends JpaRepository<DayOff, Long> {

    DayOff findByEmployeeId(Long id);
}
