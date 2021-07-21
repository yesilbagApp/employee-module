package com.employee.dto;

import com.employee.enums.Status;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class DayOffRequestDTO {

    private Long id;
    private OffsetDateTime dayOffStartDate;
    private OffsetDateTime dayOffFinishDate;
    private Status status;
    private int workDays;
    private EmployeeDTO approvedEmployee;
    private EmployeeDTO employee;
}
