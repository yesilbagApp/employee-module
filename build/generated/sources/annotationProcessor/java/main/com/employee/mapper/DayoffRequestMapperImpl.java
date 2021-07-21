package com.employee.mapper;

import com.employee.api.request.CreateDayOffRequest;
import com.employee.domain.DayOffRequest;
import com.employee.domain.Employee;
import com.employee.dto.DayOffRequestDTO;
import com.employee.dto.EmployeeDTO;
import com.employee.service.EmployeeQueryService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-21T18:23:29+0300",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.1.jar, environment: Java 1.8.0_291 (Oracle Corporation)"
)
@Component
public class DayoffRequestMapperImpl extends DayoffRequestMapper {

    private final EmployeeQueryService employeeQueryService;

    @Autowired
    public DayoffRequestMapperImpl(EmployeeQueryService employeeQueryService) {

        this.employeeQueryService = employeeQueryService;
    }

    @Override
    public DayOffRequest toEntity(CreateDayOffRequest createDayOffRequest) {
        if ( createDayOffRequest == null ) {
            return null;
        }

        DayOffRequest dayOffRequest = new DayOffRequest();

        dayOffRequest.setEmployee( employeeQueryService.getEmployeeById( createDayOffRequest.getEmployeeId() ) );
        dayOffRequest.setDayOffStartDate( createDayOffRequest.getDayOffStartDate() );
        dayOffRequest.setDayOffFinishDate( createDayOffRequest.getDayOffFinishDate() );

        return dayOffRequest;
    }

    @Override
    public DayOffRequestDTO toDTO(DayOffRequest dayoffRequest) {
        if ( dayoffRequest == null ) {
            return null;
        }

        DayOffRequestDTO dayOffRequestDTO = new DayOffRequestDTO();

        dayOffRequestDTO.setId( dayoffRequest.getId() );
        dayOffRequestDTO.setDayOffStartDate( dayoffRequest.getDayOffStartDate() );
        dayOffRequestDTO.setDayOffFinishDate( dayoffRequest.getDayOffFinishDate() );
        dayOffRequestDTO.setStatus( dayoffRequest.getStatus() );
        dayOffRequestDTO.setWorkDays( dayoffRequest.getWorkDays() );
        dayOffRequestDTO.setApprovedEmployee( employeeToEmployeeDTO( dayoffRequest.getApprovedEmployee() ) );
        dayOffRequestDTO.setEmployee( employeeToEmployeeDTO( dayoffRequest.getEmployee() ) );

        return dayOffRequestDTO;
    }

    @Override
    public List<DayOffRequestDTO> toDTOList(List<DayOffRequest> dayOffRequestList) {
        if ( dayOffRequestList == null ) {
            return null;
        }

        List<DayOffRequestDTO> list = new ArrayList<DayOffRequestDTO>( dayOffRequestList.size() );
        for ( DayOffRequest dayOffRequest : dayOffRequestList ) {
            list.add( toDTO( dayOffRequest ) );
        }

        return list;
    }

    protected EmployeeDTO employeeToEmployeeDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId( employee.getId() );
        employeeDTO.setName( employee.getName() );
        employeeDTO.setSurname( employee.getSurname() );

        return employeeDTO;
    }
}
