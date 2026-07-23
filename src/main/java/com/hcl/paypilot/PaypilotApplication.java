package com.hcl.paypilot;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


/**

* Main entry point of the PayPilot Bill Service Application.

*

* This class is responsible for bootstrapping and launching

* the Spring Boot application. The @SpringBootApplication

* annotation enables auto-configuration, component scanning,

* and configuration support for the entire project.

*

* When the application starts, Spring Boot initializes all

* required beans, configurations, controllers, services,

* repositories, and embedded server settings.

*

* @author PayPilot Team

*/

@SpringBootApplication

public class PaypilotApplication {


    /**

     * Main method used to start the Spring Boot application.

     *

     * @param args Command-line arguments passed during application startup

     */

    public static void main(String[] args) {

        SpringApplication.run(PaypilotApplication.class, args);

    }


}
 