package com.employee.mapper;

import com.employee.domain.Employee;
import com.employee.dto.EmployeeDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class EmployeeMapper {

    public static final EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Named("mapEmployee")
    public abstract EmployeeDTO toDTO(Employee employee);

    @IterableMapping(qualifiedByName = "mapEmployee")
    public abstract List<EmployeeDTO> toDTOList(List<Employee> employeeList);
}
