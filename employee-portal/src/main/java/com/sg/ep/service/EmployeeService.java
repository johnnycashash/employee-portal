package com.sg.ep.service;

import com.sg.ep.dto.request.EmployeeRequest;
import com.sg.ep.dto.response.EmployeeResponse;
import com.sg.ep.exception.EmployeeException;

import java.util.List;

/**
 * The interface Employee service.
 */
public interface EmployeeService {
    /**
     * Find all list.
     *
     * @return the list
     */
    List<EmployeeResponse> findAll();

    /**
     * Find by id employee response.
     *
     * @param id the id
     * @return the employee response
     * @throws EmployeeException the employee exception
     */
    EmployeeResponse findById(long id) throws EmployeeException;

    /**
     * Save employee response.
     *
     * @param employeeRequest the employee request
     * @return the employee response
     */
    EmployeeResponse save(EmployeeRequest employeeRequest);

    /**
     * Save all list.
     *
     * @param employeeRequests the employee requests
     * @return the list
     */
    List<EmployeeResponse> saveAll(List<EmployeeRequest> employeeRequests);
}
