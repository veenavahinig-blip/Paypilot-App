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

* Controller class responsible for handling user-related operations

* such as registration, OTP verification, login, forgot password,

* and password reset functionalities.

*

* This controller acts as an intermediary between the client

* application and the service layer by receiving HTTP requests,

* processing input data, and returning appropriate responses.

*

* @author PayPilot Team

*/

@RestController

@RequestMapping("/users")

@CrossOrigin(origins = "http://localhost:4200")

public class UserController {


    /**

     * Service layer object used to perform user-related operations

     * including registration, authentication, OTP verification,

     * and password management.

     */

    @Autowired

    private UserServiceImpl userService;


    /**

     * Registers a new user in the system and sends an OTP

     * to the registered email address for verification.

     *

     * @param user User details received from request body

     * @return ResponseEntity containing registration status message

     */

    @PostMapping("/register")

    public ResponseEntity<String> createUser(@RequestBody UserEntity user) {

        return ResponseEntity.ok(userService.registerUser(user));

    }


    /**

     * Verifies the OTP entered by the user after registration.

     * If the OTP matches, the user's account is activated.

     *

     * @param data Contains user email and OTP

     * @return Verification status message

     */

    @PostMapping("/verify")

    public String verifyOtp(@RequestBody Map<String, String> data) {

        String email = data.get("userEmail");

        String otp = data.get("otp");

        return userService.verify(email, otp);

    }


    /**

     * Authenticates the user using email, password,

     * and CAPTCHA token validation.

     *

     * @param data Contains email, password, and CAPTCHA token

     * @return Login status message

     */

    @PostMapping("/login")

    public String login(@RequestBody Map<String, String> data) {

        String email = data.get("email");

        String password = data.get("password");

        String captchaToken = data.get("captchaToken");

        return userService.login(email, password, captchaToken);

    }


    /**

     * Sends a password reset OTP to the registered

     * email address of the user.

     *

     * @param data Contains user's email address

     * @return Status message indicating OTP sending result

     */

    @PostMapping("/forgotPassword")

    public String forgotPassword(@RequestBody Map<String, String> data) {

        String email = data.get("email");

        return userService.forgotPassword(email);

    }


    /**

     * Verifies the OTP sent to the user's email during

     * the forgot password process.

     *

     * @param data Contains email and OTP

     * @return OTP verification status message

     */

    @PostMapping("/verify-forgot-otp")

    public String verifyForgotOtp(@RequestBody Map<String, String> data) {

        String email = data.get("email");

        String otp = data.get("otp");

        return userService.verifyForgotOtp(email, otp);

    }


    /**

     * Resets the user's password after successful OTP verification.

     * The new password and confirm password must match for

     * the password update to be successful.

     *

     * @param data Contains email, new password, and confirm password

     * @return Password reset status message

     */

    @PostMapping("/reset-password")

    public String resetPassword(@RequestBody Map<String, String> data) {

        String email = data.get("email");

        String newPassword = data.get("newPassword");

        String confirmPassword = data.get("confirmPassword");


        return userService.resetPassword(

                email,

                newPassword,

                confirmPassword);

    }


    /**

     * Verifies the OTP provided by the user and resets

     * the password in a single operation.

     *

     * This endpoint combines OTP verification and

     * password reset functionality for convenience.

     *

     * @param data Contains email, OTP, and new password

     * @return Status message indicating whether OTP verification

     *         and password reset were successful

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


    @GetMapping("/dashboard/{email}")

    public UserDashboardDTO getDashboard(

            @PathVariable String email) {


        return userService.getDashboard(email);


    }
     
    
    @GetMapping("/profile/{email}")

    public UserEntity getProfile(

            @PathVariable String email) {


        return userService.getUserProfile(email);

    }
     
    @PutMapping("/profile/{email}")

    public UserEntity updateProfile(


            @PathVariable String email,


            @RequestBody UserEntity user) {


        return userService

                .updateUserProfile(email, user);

    }
     
    
    

     
     
}
 