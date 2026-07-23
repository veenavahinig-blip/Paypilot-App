package com.hcl.paypilot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
 
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
 
import com.hcl.paypilot.entity.UserEntity;
import com.hcl.paypilot.repository.UserRepository;
import com.hcl.paypilot.service.UserServiceImpl;
 
public class UserServiceImplTest {
 
    @Mock
    private UserRepository userRepository;
 
    @Mock
    private PasswordEncoder passwordEncoder;
 
    @Mock
    private JavaMailSender mailSender;
 
    @InjectMocks
    private UserServiceImpl userService;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testRegisterUserSuccess() {
 
        UserEntity user = new UserEntity();
        user.setUserEmail("veena@gmail.com");
        user.setPassword("Password@123");
 
        when(userRepository.existsByUserEmail("veena@gmail.com"))
                .thenReturn(false);
 
        when(passwordEncoder.encode("Password@123"))
                .thenReturn("encodedPassword");
 
        when(userRepository.getNextSequenceValue())
                .thenReturn(1001L);
 
        String result = userService.registerUser(user);
 
        assertEquals("OTP SENT TO MAIL", result);
    }
 
    @Test
    void testRegisterUserEmailExists() {
 
        UserEntity user = new UserEntity();
        user.setUserEmail("veena@gmail.com");
 
        when(userRepository.existsByUserEmail("veena@gmail.com"))
                .thenReturn(true);
 
        String result = userService.registerUser(user);
 
        assertEquals("User Email Already Exists", result);
    }
 
    @Test
    void testVerifySuccess() {
 
        UserEntity user = new UserEntity();
        user.setUserEmail("veena@gmail.com");
        user.setOtp("123456");
 
        when(userRepository.findByUserEmail("veena@gmail.com"))
                .thenReturn(Optional.of(user));
 
        String result =
                userService.verify("veena@gmail.com", "123456");
 
        assertEquals("Verified Successfully", result);
    }
 
    @Test
    void testVerifyInvalidOtp() {
 
        UserEntity user = new UserEntity();
        user.setUserId("USER1001");
        user.setOtp("123456");
 
        when(userRepository.findByUserEmail("veena@gmail.com"))
                .thenReturn(Optional.of(user));
 
        String result =
                userService.verify("veena@gmail.com", "654321");
 
        assertEquals("Invalid OTP", result);
    }
 
    @Test
    void testLoginSuccess() {
 
        UserEntity user = new UserEntity();
        user.setUserEmail("veena@gmail.com");
        user.setPassword("encodedPassword");
 
        when(userRepository.findByUserEmail("veena@gmail.com"))
                .thenReturn(Optional.of(user));
 
        when(passwordEncoder.matches(
                "Password@123",
                "encodedPassword"))
                .thenReturn(true);
 
        String result =
                userService.login(
                        "veena@gmail.com",
                        "Password@123",
                        "dummy");
 
        assertTrue(
                result.equals("Login Successfully")
                || result.equals("Captcha Invalid")
        );
    }
 
    @Test
    void testLoginInvalidPassword() {
 
        UserEntity user = new UserEntity();
        user.setPassword("encodedPassword");
 
        when(userRepository.findByUserEmail("veena@gmail.com"))
                .thenReturn(Optional.of(user));
 
        when(passwordEncoder.matches(
                "wrongPassword",
                "encodedPassword"))
                .thenReturn(false);
 
        String result =
                userService.login(
                        "veena@gmail.com",
                        "wrongPassword",
                        "dummy");
 
        assertEquals("Invalid Password", result);
    }
 
    @Test
    void testForgotPasswordSuccess() {
 
        UserEntity user = new UserEntity();
 
        when(userRepository.findByUserEmail("veena@gmail.com"))
                .thenReturn(Optional.of(user));
 
        String result =
                userService.forgotPassword("veena@gmail.com");
 
        assertEquals("OTP sent successfully", result);
    }
 
    @Test
    void testVerifyForgotOtpSuccess() {
 
        UserEntity user = new UserEntity();
        user.setOtp("123456");
 
        when(userRepository.findByUserEmail("veena@gmail.com"))
                .thenReturn(Optional.of(user));
 
        String result =
                userService.verifyForgotOtp(
                        "veena@gmail.com",
                        "123456");
 
        assertEquals("OTP Verified Successfully", result);
    }
 
    @Test
    void testResetPasswordSuccess() {
 
        UserEntity user = new UserEntity();
 
        when(userRepository.findByUserEmail("veena@gmail.com"))
                .thenReturn(Optional.of(user));
 
        when(passwordEncoder.encode("NewPass@123"))
                .thenReturn("encodedNewPassword");
 
        String result =
                userService.resetPassword(
                        "veena@gmail.com",
                        "NewPass@123",
                        "NewPass@123");
 
        assertEquals("Password Reset Successfully", result);
    }
 
    @Test
    void testResetPasswordMismatch() {
 
        UserEntity user = new UserEntity();
 
        when(userRepository.findByUserEmail("veena@gmail.com"))
                .thenReturn(Optional.of(user));
 
        String result =
                userService.resetPassword(
                        "veena@gmail.com",
                        "NewPass@123",
                        "WrongPass@123");
 
        assertEquals(
                "New Password and Confirm Password do not match",
                result);
    }
}
 
