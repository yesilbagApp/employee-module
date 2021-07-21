package com.employee.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.OffsetDateTime;


@Entity
@Data
@Where(clause = "deleted = 0")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id")
    private Title title;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "employee_number", unique = true)
    private int employeeNumber;

    @Column(name = "work_start_date")
    private OffsetDateTime workStartDate;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = false;
}
