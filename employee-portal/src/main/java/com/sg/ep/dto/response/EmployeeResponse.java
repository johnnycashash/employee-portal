package com.sg.ep.dto.response;

import lombok.Data;

import java.sql.Date;

/**
 * The type Employee response.
 */
@Data
public class EmployeeResponse {
    private String firstName;
    private String lastName;
    private char gender;
    private Date dob;
    private String department;
}