package com.employee.domain;

import com.employee.enums.Status;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Data
@Where(clause = "deleted = 0")
public class DayOffRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "day_off_start_date")
    private OffsetDateTime dayOffStartDate;

    @Column(name = "day_off_finish_date")
    private OffsetDateTime dayOffFinishDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "approved_employee_id")
    private Employee approvedEmployee;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = false;

    @Column(name = "work_days")
    private int workDays;
}
