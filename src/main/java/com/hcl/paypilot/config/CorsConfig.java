package com.hcl.paypilot.config;


import java.util.Arrays;


import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.CorsConfigurationSource;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


/**

* Configuration class responsible for handling

* Cross-Origin Resource Sharing (CORS) settings.

*

* This configuration allows the Angular frontend

* application running on localhost:4200 to communicate

* with the Spring Boot backend without CORS-related issues.

*

* The configuration specifies allowed origins,

* HTTP methods, request headers, and credential support.

*

* @author PayPilot Team

*/

@Configuration

public class CorsConfig {


    /**

     * Creates and configures a CORS configuration source.

     *

     * This bean defines:

     * <ul>

     * <li>Allowed frontend origins</li>

     * <li>Allowed HTTP methods</li>

     * <li>Allowed request headers</li>

     * <li>Credential support for authentication/session handling</li>

     * </ul>

     *

     * The configuration is applied to all application endpoints.

     *

     * @return Configured CorsConfigurationSource object

     */

    @Bean

    public CorsConfigurationSource corsConfigurationSource() {


        CorsConfiguration config = new CorsConfiguration();


        /**

         * Specifies the allowed frontend origins.

         */

        config.setAllowedOrigins(

                Arrays.asList("http://localhost:4200"));


        /**

         * Specifies the allowed HTTP request methods.

         */

        config.setAllowedMethods(

                Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));


        /**

         * Allows all request headers.

         */

        config.setAllowedHeaders(

                Arrays.asList("*"));


        /**

         * Enables sending credentials such as cookies,

         * authorization headers, and session information.

         */

        config.setAllowCredentials(true);


        /**

         * Registers the CORS configuration for all endpoints.

         */

        UrlBasedCorsConfigurationSource source =

                new UrlBasedCorsConfigurationSource();


        source.registerCorsConfiguration("/**", config);


        return source;

    }

}
 