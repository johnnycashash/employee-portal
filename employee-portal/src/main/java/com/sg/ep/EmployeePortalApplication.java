package com.sg.ep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type Employee portal application.
 */
@EnableSwagger2
@SpringBootApplication
public class EmployeePortalApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(EmployeePortalApplication.class, args);
    }

}
