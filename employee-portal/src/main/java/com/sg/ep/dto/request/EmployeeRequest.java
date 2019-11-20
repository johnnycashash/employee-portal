package com.sg.ep.dto.request;

import lombok.Data;

import java.sql.Date;

/**
 * The type Employee request.
 */
@Data
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private char gender;
    private Date dob;
    private String department;
}