package com.employee.domain;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Data
@Where(clause = "deleted = 0")
public class DayOff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "annual_permit")
    private int annualPermit;

    @Column(name = "start_date")
    private OffsetDateTime startDate;

    @Column(name = "finish_date")
    private OffsetDateTime finishDate;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = false;
}
