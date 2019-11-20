package com.sg.ep.service;

import com.sg.ep.dto.request.EmployeeRequest;
import com.sg.ep.dto.response.EmployeeResponse;
import com.sg.ep.exception.EmployeeException;
import com.sg.ep.model.Employee;
import com.sg.ep.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


/**
 * The type Employee service test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    @Mock
    private EmployeeRepository employeeRepository;

    /**
     * Gets employee.
     *
     * @return the employee
     */
    public Employee getEmployee() {
        Employee employee = new Employee();
        employee.setDepartment("Dept");
        employee.setDob(new Date(1993, 1, 1));
        employee.setFirstName("FN");
        employee.setGender('m');
        employee.setLastName("LN");
        employee.setId(1);
        return employee;
    }

    /**
     * Gets employees.
     *
     * @return the employees
     */
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(getEmployee());
        return employees;
    }

    /**
     * Test unit get employee.
     */
    @Test
    public void testUnitGetEmployees() {
        when(employeeRepository.findAll()).thenReturn(getEmployees());
        List<EmployeeResponse> employeeResponses = employeeService.findAll();
        Assert.assertNotNull(employeeResponses);
        Assert.assertEquals(employeeResponses.size(), 1);

    }

    /**
     * Test unit get employee by id.
     *
     * @throws EmployeeException the employee exception
     */
    @Test
    public void testUnitGetEmployeeById() throws EmployeeException {
        when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getEmployee()));
        EmployeeResponse employeeResponse = employeeService.findById(1);
        Assert.assertNotNull(employeeResponse);
    }

    /**
     * Test unit get employee by id employee exception.
     *
     * @throws EmployeeException the employee exception
     */
    @Test(expected = EmployeeException.class)
    public void testUnitGetEmployeeByIdEmployeeException() throws EmployeeException {
        when(employeeRepository.findById(Mockito.anyLong())).thenThrow(new EmployeeException("Not found"));
        employeeService.findById(1);
    }

    /**
     * Test unit post employees.
     */
    @Test
    public void testUnitPostEmployees() {
        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(getEmployee());
        EmployeeResponse employeeResponse = employeeService.save(new EmployeeRequest());
        Assert.assertNotNull(employeeResponse);
    }

    /**
     * Test unit post employee.
     */
    @Test
    public void testUnitPostEmployee() {
        when(employeeRepository.saveAll(Mockito.anyList())).thenReturn(getEmployees());
        List<EmployeeResponse> employeeResponses = employeeService.saveAll(new ArrayList<>());
        Assert.assertNotNull(employeeResponses);
        Assert.assertEquals(employeeResponses.size(), 1);
    }
}
