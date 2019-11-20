package com.sg.ep.utils;

import com.sg.ep.dto.request.EmployeeRequest;
import com.sg.ep.dto.response.EmployeeResponse;
import com.sg.ep.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * The interface Employee mapper.
 */
@Mapper
public interface EmployeeMapper {
    /**
     * The constant INSTANCE.
     */
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    /**
     * Employee to employee response employee response.
     *
     * @param source the source
     * @return the employee response
     */
    EmployeeResponse employeeToEmployeeResponse(Employee source);

    /**
     * Employees to employee responses list.
     *
     * @param source the source
     * @return the list
     */
    List<EmployeeResponse> employeesToEmployeeResponses(List<Employee> source);

    /**
     * Employee request to employee employee.
     *
     * @param employeeRequest the employee request
     * @return the employee
     */
    Employee employeeRequestToEmployee(EmployeeRequest employeeRequest);

    /**
     * Employee requests to employees list.
     *
     * @param employeeRequest the employee request
     * @return the list
     */
    List<Employee> employeeRequestsToEmployees(List<EmployeeRequest> employeeRequest);

}