package com.employee.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@ApiModel
public class CreateDayOffRequest {

    private Long employeeId;
    private OffsetDateTime dayOffStartDate;
    private OffsetDateTime dayOffFinishDate;
}
