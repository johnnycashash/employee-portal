package com.sg.ep.controller;

import com.sg.ep.TestConstant;
import com.sg.ep.dto.request.EmployeeRequest;
import com.sg.ep.dto.response.EmployeeResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Employee portal controller test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EmployeePortalControllerTest {
    /**
     * The Random server port.
     */
    @LocalServerPort
    int randomServerPort;
    /**
     * The Data source.
     */
    @Autowired
    DataSource dataSource;
    /**
     * The Test rest template.
     */
    @Autowired
    TestRestTemplate testRestTemplate;

    private void populateData() throws SQLException {
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(TestConstant.EMPLOYEE_SEQUENCE);
            connection.commit();
            statement.execute(TestConstant.EMPLOYEE_TABLE_CREATE);
            connection.commit();
            statement.executeUpdate(TestConstant.EMPLOYEE_DATA_INSERT);
            connection.commit();
        }
    }

    /**
     * Init.
     *
     * @throws SQLException the sql exception
     */
    @Before
    public void init() throws SQLException {
        populateData();
    }

    /**
     * Test get employee.
     */
    @Test
    public void testGetEmployees() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<List<EmployeeResponse>> employeeResponseResponseEntity =
                testRestTemplate.exchange("http://localhost:" + randomServerPort + "/api/v1/employees", HttpMethod.GET, new HttpEntity<>(requestHeaders), new ParameterizedTypeReference<List<EmployeeResponse>>() {
                });
        Assert.assertEquals(employeeResponseResponseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(employeeResponseResponseEntity.getBody());
        Assert.assertEquals(employeeResponseResponseEntity.getBody().size(), 1);
    }

    /**
     * Test get employee by id.
     */
    @Test
    public void testGetEmployeeById() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<EmployeeResponse> employeeResponseResponseEntity =
                testRestTemplate.exchange("http://localhost:" + randomServerPort + "/api/v1/employee/0", HttpMethod.GET, new HttpEntity<>(requestHeaders), EmployeeResponse.class);
        Assert.assertEquals(employeeResponseResponseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(employeeResponseResponseEntity.getBody());
    }

    /**
     * Test post employees.
     */
    @Test
    public void testPostEmployees() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setDepartment("sdsd");
        employeeRequest.setDob(new Date(1993, 1, 1));
        employeeRequest.setFirstName("sdsd");
        employeeRequest.setLastName("sdsd");
        employeeRequest.setGender('m');
        ArrayList<EmployeeRequest> employeeRequests = new ArrayList<>();
        employeeRequests.add(employeeRequest);
        ResponseEntity<List<EmployeeResponse>> employeeResponseResponseEntity =
                testRestTemplate.exchange("http://localhost:" + randomServerPort + "/api/v1/employees", HttpMethod.POST, new HttpEntity<>(employeeRequests, requestHeaders), new ParameterizedTypeReference<List<EmployeeResponse>>() {
                });
        Assert.assertEquals(employeeResponseResponseEntity.getStatusCode(), HttpStatus.CREATED);
        Assert.assertNotNull(employeeResponseResponseEntity.getBody());
        Assert.assertEquals(employeeResponseResponseEntity.getBody().size(), 1);
    }

    /**
     * Test post employee.
     */
    @Test
    public void testPostEmployee() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setDepartment("sdsd");
        employeeRequest.setDob(new Date(1993, 1, 1));
        employeeRequest.setFirstName("sdsd");
        employeeRequest.setLastName("sdsd");
        employeeRequest.setGender('m');
        ResponseEntity<EmployeeResponse> employeeResponseResponseEntity =
                testRestTemplate.exchange("http://localhost:" + randomServerPort + "/api/v1/employee", HttpMethod.POST, new HttpEntity<>(employeeRequest, requestHeaders), new ParameterizedTypeReference<EmployeeResponse>() {
                });
        Assert.assertEquals(employeeResponseResponseEntity.getStatusCode(), HttpStatus.CREATED);
        Assert.assertNotNull(employeeResponseResponseEntity.getBody());
    }

    /**
     * Destroy test data.
     *
     * @throws SQLException the sql exception
     */
    @After
    public void destroyTestData() throws SQLException {
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(TestConstant.DROP_HSQLDB_SCHEMA);
            connection.commit();
        }
    }
}
