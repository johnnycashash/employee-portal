package com.sg.ep.service;

import com.sg.ep.dto.request.EmployeeRequest;
import com.sg.ep.dto.response.EmployeeResponse;
import com.sg.ep.exception.EmployeeException;
import com.sg.ep.model.Employee;
import com.sg.ep.repository.EmployeeRepository;
import com.sg.ep.utils.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Employee service.
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private
    EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeResponse> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return EmployeeMapper.INSTANCE.employeesToEmployeeResponses(employees);
    }

    @Override
    public EmployeeResponse findById(long id) throws EmployeeException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return EmployeeMapper.INSTANCE.employeeToEmployeeResponse(employee.get());
        } else {
            throw new EmployeeException("Employee with id Not found" + id);
        }
    }

    @Override
    public EmployeeResponse save(EmployeeRequest employeeRequest) {
        Employee employee = EmployeeMapper.INSTANCE.employeeRequestToEmployee(employeeRequest);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.INSTANCE.employeeToEmployeeResponse(savedEmployee);
    }

    @Override
    public List<EmployeeResponse> saveAll(List<EmployeeRequest> employeeRequests) {
        List<Employee> employees = EmployeeMapper.INSTANCE.employeeRequestsToEmployees(employeeRequests);
        List<Employee> savedEmployees = employeeRepository.saveAll(employees);
        return EmployeeMapper.INSTANCE.employeesToEmployeeResponses(savedEmployees);
    }
}