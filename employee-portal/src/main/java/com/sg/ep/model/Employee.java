package com.sg.ep.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * The type Employee.
 */
@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    @NotNull(message = "First Name is mandatory")
    private String firstName;
    @Column
    private String lastName;
    @Column
    @NotNull(message = "Gender is mandatory")
    private char gender;
    @Column
    @NotNull(message = "DOB is mandatory")
    private Date dob;
    @Column
    @NotNull(message = "Department is mandatory")
    private String department;

}