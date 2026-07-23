package com.hcl.paypilot.service;
 
import com.hcl.paypilot.dto.UserDashboardDTO;
import com.hcl.paypilot.entity.UserEntity;
 
/**
* Service interface for managing user-related operations
* in the PayPilot application.
*
* This interface defines methods for user registration,
* login, OTP generation, OTP verification, forgot password,
* and password reset functionalities.
*
* @author PayPilotTeam
* @version 1.0
*/
public interface UserService {
 
    /**
     * Generates a random 6-digit OTP.
     *
     * @return generated OTP
     */
    public String generateOTP();
 
    /**
     * Sends OTP to the specified email address.
     *
     * @param email Recipient email address
     * @param otp   One Time Password
     */
    public void sendOTP(String email, String otp);
 
    /**
     * Verifies the OTP entered by the user during registration.
     *
     * @param email User email
     * @param otp   Entered OTP
     * @return verification result message
     */
    public String verify(String email, String otp);
 
    /**
     * Registers a new user in the system.
     *
     * @param user User details
     * @return registration status message
     */
    public String registerUser(UserEntity user);
 
    /**
     * Authenticates user login credentials.
     *
     * @param email    User email address
     * @param password User password
     * @return login status message
     */
    public String login(String email, String password);
 
    /**
     * Generates and sends OTP for forgot password functionality.
     *
     * @param email User email address
     * @return status message indicating OTP sent or failure
     */
    public String forgotPassword(String email);
 
    /**
     * Verifies the OTP sent during forgot password process.
     *
     * @param email User email address
     * @param otp   Entered OTP
     * @return OTP verification result
     */
    public String verifyForgotOtp(String email, String otp);
 
    /**
     * Resets the user's password after successful OTP verification.
     *
     * @param email            User email address
     * @param newPassword      New password entered by user
     * @param confirmPassword  Confirm password entered by user
     * @return password reset status message
     */
    public String resetPassword(
            String email,
            String newPassword,
            String confirmPassword
    );
    
    public UserDashboardDTO getDashboard(String userId);
    
    public UserEntity getUserProfile(String email);


    public UserEntity updateUserProfile(String email, UserEntity user);
     
    
}
 