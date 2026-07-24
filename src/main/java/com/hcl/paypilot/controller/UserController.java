package com.hcl.paypilot.controller;


import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.hcl.paypilot.dto.UserDashboardDTO;

import com.hcl.paypilot.entity.UserEntity;

import com.hcl.paypilot.service.UserServiceImpl;


/**

* ============================================================================

* User Controller

* ============================================================================

*

* This controller exposes REST APIs for user management within the

* PayPilot Application.

*

* Features:

* - User Registration

* - OTP Verification

* - User Login

* - Forgot Password

* - Forgot Password OTP Verification

* - Password Reset

* - OTP Verification and Password Reset

* - Dashboard Retrieval

* - User Profile Retrieval

* - User Profile Update

*

* Base URL:

* http://localhost:8086/users

*

* Author: PayPilot Team

* ============================================================================

*/


@RestController

@RequestMapping("/users")

@CrossOrigin(origins = "http://localhost:4200")

public class UserController {


    /**

     * Service layer dependency responsible for handling

     * user registration, authentication, profile management,

     * OTP verification, dashboard operations, and password management.

     */

    @Autowired

    private UserServiceImpl userService;


    /**

     * =========================================================================

     * Register User

     * =========================================================================

     *

     * Endpoint:

     * POST /users/register

     *

     * Registers a new user in the application.

     * After successful registration, an OTP is sent

     * to the user's registered email address for verification.

     *

     * Example:

     * POST /users/register

     *

     * @param user User registration details

     * @return Registration status message

     */

    @PostMapping("/register")

    public ResponseEntity<String> createUser(

            @RequestBody UserEntity user) {


        return ResponseEntity.ok(

                userService.registerUser(user));


    }


    /**

     * =========================================================================

     * Verify Registration OTP

     * =========================================================================

     *

     * Endpoint:

     * POST /users/verify

     *

     * Verifies the OTP entered by the user after registration.

     * Upon successful verification, the user account is activated.

     *

     * Example:

     * POST /users/verify

     *

     * Request Body:

     * {

     *   "userEmail":"user@gmail.com",

     *   "otp":"123456"

     * }

     *

     * @param data Contains user email and OTP

     * @return Verification status message

     */

    @PostMapping("/verify")

    public String verifyOtp(

            @RequestBody Map<String, String> data) {


        String email = data.get("userEmail");

        String otp = data.get("otp");


        return userService.verify(email, otp);


    }


    /**

     * =========================================================================

     * User Login

     * =========================================================================

     *

     * Endpoint:

     * POST /users/login

     *

     * Authenticates a user using:

     * - Email

     * - Password

     * - Google reCAPTCHA Token

     *

     * Example:

     * POST /users/login

     *

     * @param data Contains email, password and captcha token

     * @return Login status message

     */

    @PostMapping("/login")

    public String login(

            @RequestBody Map<String, String> data) {


        String email = data.get("email");

        String password = data.get("password");

        String captchaToken = data.get("captchaToken");


        return userService.login(

                email,

                password,

                captchaToken);


    }


    /**

     * =========================================================================

     * Forgot Password

     * =========================================================================

     *

     * Endpoint:

     * POST /users/forgotPassword

     *

     * Sends an OTP to the user's registered email address

     * to initiate the password reset process.

     *

     * Example:

     * POST /users/forgotPassword

     *

     * @param data Contains user email address

     * @return Status message

     */

    @PostMapping("/forgotPassword")

    public String forgotPassword(

            @RequestBody Map<String, String> data) {


        String email = data.get("email");


        return userService.forgotPassword(email);


    }


    /**

     * =========================================================================

     * Verify Forgot Password OTP

     * =========================================================================

     *

     * Endpoint:

     * POST /users/verify-forgot-otp

     *

     * Verifies the OTP sent during the forgot password process.

     *

     * Example:

     * POST /users/verify-forgot-otp

     *

     * @param data Contains email and OTP

     * @return OTP verification status message

     */

    @PostMapping("/verify-forgot-otp")

    public String verifyForgotOtp(

            @RequestBody Map<String, String> data) {


        String email = data.get("email");

        String otp = data.get("otp");


        return userService.verifyForgotOtp(

                email,

                otp);


    }


    /**

     * =========================================================================

     * Reset Password

     * =========================================================================

     *

     * Endpoint:

     * POST /users/reset-password

     *

     * Resets the user's password after successful

     * forgot password verification.

     *

     * Example:

     * POST /users/reset-password

     *

     * @param data Contains email, new password and confirm password

     * @return Password reset status message

     */

    @PostMapping("/reset-password")

    public String resetPassword(

            @RequestBody Map<String, String> data) {


        String email = data.get("email");

        String newPassword = data.get("newPassword");

        String confirmPassword = data.get("confirmPassword");


        return userService.resetPassword(

                email,

                newPassword,

                confirmPassword);


    }


    /**

     * =========================================================================

     * Verify OTP And Reset Password

     * =========================================================================

     *

     * Endpoint:

     * POST /users/verifyOtpAndResetPassword

     *

     * Verifies the OTP and resets the password

     * in a single operation.

     *

     * Example:

     * POST /users/verifyOtpAndResetPassword

     *

     * @param data Contains email, OTP and new password

     * @return Verification and password reset status message

     */

    @PostMapping("/verifyOtpAndResetPassword")

    public String verifyOtpAndResetPassword(

            @RequestBody Map<String, String> data) {


        String email = data.get("email");

        String otp = data.get("otp");

        String newPassword = data.get("newPassword");


        return userService.verifyOtpAndResetPassword(

                email,

                otp,

                newPassword);


    }


    /**

     * =========================================================================

     * User Dashboard

     * =========================================================================

     *

     * Endpoint:

     * GET /users/dashboard/{email}

     *

     * Retrieves dashboard summary data for a user.

     *

     * Dashboard information may include:

     * - Total Bills

     * - Paid Bills

     * - Pending Bills

     * - Upcoming Bills

     * - Payment Statistics

     *

     * Example:

     * GET /users/dashboard/user@gmail.com

     *

     * @param email User email address

     * @return Dashboard details

     */

    @GetMapping("/dashboard/{email}")

    public UserDashboardDTO getDashboard(

            @PathVariable String email) {


        return userService.getDashboard(email);


    }


    /**

     * =========================================================================

     * Get User Profile

     * =========================================================================

     *

     * Endpoint:

     * GET /users/profile/{email}

     *

     * Retrieves user profile details using email address.

     *

     * Example:

     * GET /users/profile/user@gmail.com

     *

     * @param email User email address

     * @return User profile information

     */

    @GetMapping("/profile/{email}")

    public UserEntity getProfile(

            @PathVariable String email) {


        return userService.getUserProfile(email);


    }


    /**

     * =========================================================================

     * Update User Profile

     * =========================================================================

     *

     * Endpoint:

     * PUT /users/profile/{email}

     *

     * Updates user profile details.

     *

     * Example:

     * PUT /users/profile/user@gmail.com

     *

     * @param email User email address

     * @param user Updated user profile details

     * @return Updated user information

     */

    @PutMapping("/profile/{email}")

    public UserEntity updateProfile(


            @PathVariable String email,


            @RequestBody UserEntity user) {


        return userService.updateUserProfile(

                email,

                user);


    }


}
 