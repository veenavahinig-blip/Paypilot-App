package com.hcl.paypilot.service;


import com.hcl.paypilot.dto.UserDashboardDTO;

import com.hcl.paypilot.entity.UserEntity;


/**

* ============================================================================

* User Service

* ============================================================================

*

* Service interface responsible for defining business operations

* related to user management within the PayPilot Application.

*

* This interface acts as a contract between the Controller Layer

* and the Service Implementation Layer.

*

* Features Supported:

* - User Registration

* - OTP Generation

* - OTP Verification

* - User Authentication

* - Forgot Password

* - Password Reset

* - Dashboard Management

* - User Profile Management

* - Account Verification

*

* Author: PayPilot Team

* ============================================================================

*/

public interface UserService {


    /**

     * =========================================================================

     * Generate OTP

     * =========================================================================

     *

     * Generates a secure random OTP for user verification.

     *

     * Common Usage:

     * - User Registration

     * - Email Verification

     * - Forgot Password Verification

     *

     * @return Generated OTP

     */

    String generateOTP();


    /**

     * =========================================================================

     * Send OTP

     * =========================================================================

     *

     * Sends the generated OTP to the specified email address.

     *

     * Common Usage:

     * - Registration Verification

     * - Password Reset Verification

     *

     * @param email Recipient Email Address

     * @param otp Generated OTP

     */

    void sendOTP(

            String email,

            String otp);


    /**

     * =========================================================================

     * Verify Registration OTP

     * =========================================================================

     *

     * Verifies the OTP entered by the user during

     * account registration.

     *

     * If verification succeeds:

     * - User account becomes active

     * - User status is marked as verified

     *

     * @param email User Email Address

     * @param otp Entered OTP

     * @return Verification status message

     */

    String verify(

            String email,

            String otp);


    /**

     * =========================================================================

     * Register User

     * =========================================================================

     *

     * Registers a new user in the system.

     *

     * Registration Process:

     * - Validate user details

     * - Generate unique user ID

     * - Generate OTP

     * - Send verification email

     * - Store user details

     *

     * @param user User Details

     * @return Registration status message

     */

    String registerUser(UserEntity user);


    /**

     * =========================================================================

     * User Login

     * =========================================================================

     *

     * Authenticates user credentials.

     *

     * Validation Includes:

     * - Email Validation

     * - Password Verification

     * - Account Verification Status

     *

     * @param email User Email Address

     * @param password User Password

     * @return Login status message

     */

    String login(

            String email,

            String password);


    /**

     * =========================================================================

     * Forgot Password

     * =========================================================================

     *

     * Initiates forgot password process by generating

     * and sending an OTP to the user's registered email.

     *

     * @param email User Email Address

     * @return OTP generation status message

     */

    String forgotPassword(String email);


    /**

     * =========================================================================

     * Verify Forgot Password OTP

     * =========================================================================

     *

     * Verifies the OTP sent during the forgot password process.

     *

     * @param email User Email Address

     * @param otp Entered OTP

     * @return OTP verification status message

     */

    String verifyForgotOtp(

            String email,

            String otp);


    /**

     * =========================================================================

     * Reset Password

     * =========================================================================

     *

     * Resets the user's password after successful OTP verification.

     *

     * Validation Includes:

     * - Email Verification

     * - Password Match Verification

     * - New Password Update

     *

     * @param email User Email Address

     * @param newPassword New Password

     * @param confirmPassword Confirm Password

     * @return Password reset status message

     */

    String resetPassword(

            String email,

            String newPassword,

            String confirmPassword);


    /**

     * =========================================================================

     * User Dashboard

     * =========================================================================

     *

     * Retrieves dashboard summary information

     * for the specified user.

     *

     * Dashboard Information May Include:

     * - Total Bills

     * - Paid Bills

     * - Pending Bills

     * - Overdue Bills

     * - Recent Bills

     *

     * @param userId User Identifier

     * @return Dashboard Summary Data

     */

    UserDashboardDTO getDashboard(

            String userId);


    /**

     * =========================================================================

     * Get User Profile

     * =========================================================================

     *

     * Retrieves complete user profile details

     * using the registered email address.

     *

     * @param email User Email Address

     * @return User Profile Details

     */

    UserEntity getUserProfile(

            String email);


    /**

     * =========================================================================

     * Update User Profile

     * =========================================================================

     *

     * Updates user profile information.

     *

     * Updatable Information:

     * - User Name

     * - Gender

     * - PAN Details

     * - Bank Details

     * - IFSC Code

     * - Banking Partner

     *

     * @param email User Email Address

     * @param user Updated User Information

     * @return Updated User Entity

     */

    UserEntity updateUserProfile(

            String email,

            UserEntity user);


}
 