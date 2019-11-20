package com.sg.ep.controller;

import com.sg.ep.dto.request.EmployeeRequest;
import com.sg.ep.dto.response.EmployeeResponse;
import com.sg.ep.exception.EmployeeException;
import com.sg.ep.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Employee controller.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class EmployeeController {
    /**
     * The Employee service.
     */
    @Autowired
    EmployeeService employeeService;

    /**
     * Gets all employee.
     *
     * @return the all employee
     */
    @GetMapping(path = "/employees")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployee() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    /**
     * Gets employee by id.
     *
     * @param id the id
     * @return the employee by id
     * @throws EmployeeException the employee exception
     */
    @GetMapping(path = "/employee/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable(value = "id") Long id) throws EmployeeException {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    /**
     * Save employee response entity.
     *
     * @param employeeRequest the employee request
     * @return the response entity
     */
    @PostMapping(path = "/employee")
    public ResponseEntity<EmployeeResponse> saveEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return new ResponseEntity<>(employeeService.save(employeeRequest), HttpStatus.CREATED);
    }

    /**
     * Save employees response entity.
     *
     * @param employeeRequests the employee requests
     * @return the response entity
     */
    @PostMapping(path = "/employees")
    public ResponseEntity<List<EmployeeResponse>> saveEmployees(@Valid @RequestBody List<EmployeeRequest> employeeRequests) {
        return new ResponseEntity<>(employeeService.saveAll(employeeRequests), HttpStatus.CREATED);
    }
}
