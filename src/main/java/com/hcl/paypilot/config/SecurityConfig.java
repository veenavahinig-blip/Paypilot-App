package com.hcl.paypilot.config;


import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;


/**

* Security configuration class for the PayPilot application.

*

* This class is responsible for configuring Spring Security

* settings such as authentication, authorization, password

* encryption, CORS support, and CSRF protection.

*

* The configuration allows public access to user-related

* endpoints including registration, login, OTP verification,

* and password reset operations.

*

* @author PayPilot Team

*/

@Configuration

public class SecurityConfig {


    /**

     * Configures the security filter chain for the application.

     *

     * This method:

     * <ul>

     * <li>Enables CORS support</li>

     * <li>Disables CSRF protection for REST APIs</li>

     * <li>Allows access to public endpoints without authentication</li>

     * <li>Permits all remaining requests</li>

     * </ul>

     *

     * @param http HttpSecurity object used for security configuration

     * @return Configured SecurityFilterChain object

     * @throws Exception if a security configuration error occurs

     */

    @Bean

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http


            /**

             * Enables Cross-Origin Resource Sharing (CORS)

             * configuration for frontend-backend communication.

             */

            .cors(cors -> {})


            /**

             * Disables CSRF protection because the application

             * uses REST APIs instead of server-side forms.

             */

            .csrf(csrf -> csrf.disable())


            /**

             * Configures authorization rules for application endpoints.

             */

            .authorizeHttpRequests(auth -> auth


                    /**

                     * Publicly accessible endpoints that do not require

                     * user authentication.

                     */

                    .requestMatchers(


                            "/users/register",


                            "/users/login",


                            "/users/forgotPassword",


                            "/users/verify-forgot-otp",


                            "/users/reset-password",


                            "/users/verify"


                    ).permitAll()


                    /**

                     * Allows access to all remaining requests.

                     */

                    .anyRequest().permitAll()


            );


        return http.build();


    }


    /**

     * Creates a PasswordEncoder bean for encrypting and

     * validating user passwords.

     *

     * BCrypt is used because it is a strong one-way hashing

     * algorithm that provides secure password storage.

     *

     * @return BCryptPasswordEncoder instance

     */

    @Bean

    public PasswordEncoder passwordEncoder() {


        return new BCryptPasswordEncoder();


    }


}
 