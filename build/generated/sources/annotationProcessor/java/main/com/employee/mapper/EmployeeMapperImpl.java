package com.employee.mapper;

import com.employee.domain.Employee;
import com.employee.dto.EmployeeDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-21T18:23:29+0300",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.1.jar, environment: Java 1.8.0_291 (Oracle Corporation)"
)
public class EmployeeMapperImpl extends EmployeeMapper {

    @Override
    public EmployeeDTO toDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId( employee.getId() );
        employeeDTO.setName( employee.getName() );
        employeeDTO.setSurname( employee.getSurname() );

        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> toDTOList(List<Employee> employeeList) {
        if ( employeeList == null ) {
            return null;
        }

        List<EmployeeDTO> list = new ArrayList<EmployeeDTO>( employeeList.size() );
        for ( Employee employee : employeeList ) {
            list.add( toDTO( employee ) );
        }

        return list;
    }
}
