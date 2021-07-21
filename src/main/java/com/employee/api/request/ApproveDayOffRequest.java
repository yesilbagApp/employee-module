package com.employee.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class ApproveDayOffRequest {

    Long dayOffRequestId;
}
