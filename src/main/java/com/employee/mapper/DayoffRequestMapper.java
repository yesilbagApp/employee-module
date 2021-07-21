package com.employee.mapper;

import com.employee.api.request.CreateDayOffRequest;
import com.employee.domain.DayOffRequest;
import com.employee.dto.DayOffRequestDTO;
import com.employee.service.EmployeeQueryService;
import org.mapstruct.*;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR, uses = {EmployeeQueryService.class, EmployeeMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class DayoffRequestMapper {

    @InheritInverseConfiguration
    @Mapping(source = "createDayOffRequest.employeeId", target = "employee")
    public abstract DayOffRequest toEntity(CreateDayOffRequest createDayOffRequest);

    @Named("mapDayOffRequest")
    public abstract DayOffRequestDTO toDTO(DayOffRequest dayoffRequest);

    @IterableMapping(qualifiedByName = "mapDayOffRequest")
    public abstract List<DayOffRequestDTO> toDTOList(List<DayOffRequest> dayOffRequestList);
}
