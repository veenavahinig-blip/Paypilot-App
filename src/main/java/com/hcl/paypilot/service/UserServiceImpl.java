package com.hcl.paypilot.service;
 
import java.net.URI;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.paypilot.dto.UserDashboardDTO;
import com.hcl.paypilot.entity.BillEntity;
import com.hcl.paypilot.entity.NotificationEntity;
import com.hcl.paypilot.entity.RecaptchaResponse;
import com.hcl.paypilot.entity.UserEntity;
import com.hcl.paypilot.repository.BillRepository;
import com.hcl.paypilot.repository.UserRepository;
 
/**
* Service implementation class responsible for handling
* user authentication, registration, OTP verification,
* password management, and Google reCAPTCHA validation.
*
* This class communicates with the repository layer for
* database operations and uses JavaMailSender for email
* notifications.
*
* @author PayPilotTeam
*/
@Service
public class UserServiceImpl implements UserService {
 
    /**
     * Repository used for user-related database operations.
     */
    @Autowired
    private UserRepository userRepository;
 
    /**
     * Password encoder used to encrypt passwords before storing.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    /**
     * Mail sender used for sending OTP emails.
     */
    @Autowired
    private JavaMailSender mailSender;
 
    /**
     * Generates a random 6-digit OTP.
     *
     * @return generated OTP as String
     */
    
    @Autowired
    private BillRepository billRepository;
    
    @Autowired
    private NotificationService notificationService;
    
    public String generateOTP() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }
 
    /**
     * Sends OTP to the specified email address.
     *
     * @param email recipient email address
     * @param otp   generated OTP
     */
    public void sendOTP(String email, String otp) {
 
        SimpleMailMessage msg = new SimpleMailMessage();
 
        msg.setTo(email);
        msg.setSubject("Your OTP Code");
        msg.setText("Your OTP is: " + otp);
 
        mailSender.send(msg);
    }
 
    /**
     * Verifies OTP submitted by the user during registration.
     * If verification succeeds, the user's account is marked
     * as verified. Otherwise, the user record is deleted.
     *
     * @param email user's email address
     * @param otp   OTP entered by the user
     * @return verification result message
     */
    public String verify(String email, String otp) {
 
        Optional<UserEntity> optionalUser =
                userRepository.findByUserEmail(email);
 
        UserEntity user = optionalUser.orElse(null);
 
        if (user == null) {
            return "Entered email does not exist.";
        }
 
        if (user.getOtp().equals(otp)) {
 
            user.setVerified(true);
            userRepository.save(user);
 
            return "Verified Successfully";
        }
 
        userRepository.deleteById(user.getUserId());
 
        return "Invalid OTP";
    }
 
    /**
     * Registers a new user in the system.
     * Generates user ID, encrypts password,
     * sends OTP for email verification,
     * and stores user information.
     *
     * @param user user registration details
     * @return registration status message
     */
    public String registerUser(UserEntity user) {
 
        if (userRepository.existsByUserEmail(user.getUserEmail())) {
            return "User Email Already Exists";
        }
 
        // Encrypt password before storing
        String encodedPassword =
                passwordEncoder.encode(user.getPassword());
 
        user.setPassword(encodedPassword);
 
        // Generate unique user ID
        Long seq = userRepository.getNextSequenceValue();
        String userId = "USER" + seq;
 
        user.setUserId(userId);
 
        // Assign default user role
        user.setRole("USER");
 
        // Generate OTP for verification
        String otp = generateOTP();
 
        user.setOtp(otp);
        user.setVerified(false);
 
        // Send OTP through email
        sendOTP(user.getUserEmail(), otp);
 
        // Save user details
        userRepository.save(user);
 
        return "OTP SENT TO MAIL";
    }
 
    /**
     * Authenticates user login credentials and validates
     * Google reCAPTCHA token.
     *
     * @param email         user's email address
     * @param password      user password
     * @param captchaToken  Google reCAPTCHA token
     * @return login status message
     */
    public String login(String email, String password,
                        String captchaToken) {
 
        Optional<UserEntity> optionalUser =
                userRepository.findByUserEmail(email);
 
        if (optionalUser.isEmpty()) {
            return "Email does not exist";
        }
 
        UserEntity user = optionalUser.get();
 
        // Validate password
        if (!passwordEncoder.matches(
                password, user.getPassword())) {
            return "Invalid Password";
        }
 
        // Validate reCAPTCHA
        boolean captchaValid = verifyCaptcha(captchaToken);
 
        if (!captchaValid) {
            return "Captcha Invalid";
        }
        
        notificationService.saveNotification(user.getUserId(), "Login Successfully");
        
 
        return "Login Successfully";
    }
 
    /**
     * Generates and sends OTP for password recovery.
     *
     * @param email user's registered email address
     * @return status message
     */
    public String forgotPassword(String email) {
         System.out.println("Email     :"+email);
    	 Optional<UserEntity> optionalUser =
                 userRepository.findByUserEmail(email);
  
         if (optionalUser.isEmpty()) {
             return "Email does not exist";
         }
 
        UserEntity user = optionalUser.get();
 
        String otp = generateOTP();
 
        user.setOtp(otp);
 
        userRepository.save(user);
 
        sendOTP(email, otp);
 
        return "OTP sent successfully";
    }
 
    /**
     * Verifies OTP entered during forgot password process.
     *
     * @param email user's email address
     * @param otp   OTP entered by the user
     * @return verification status message
     */
    public String verifyForgotOtp(String email, String otp) {
 
        Optional<UserEntity> optionalUser =
                userRepository.findByUserEmail(email);
 
        if (optionalUser.isEmpty()) {
            return "Entered Email does not exist";
        }
 
        UserEntity user = optionalUser.get();
 
        if (user.getOtp().equals(otp)) {
            return "OTP Verified Successfully";
        }
 
        return "Invalid OTP";
    }
 
    /**
     * Resets user password after successful verification.
     *
     * @param email            user's email address
     * @param newPassword      new password
     * @param confirmPassword  confirmation password
     * @return password reset status message
     */
    public String resetPassword(String email,
                                String newPassword,
                                String confirmPassword) {
 
        Optional<UserEntity> optionalUser =
                userRepository.findByUserEmail(email);
 
        if (optionalUser.isEmpty()) {
            return "Entered Email does not exist";
        }
 
        if (!newPassword.equals(confirmPassword)) {
            return "New Password and Confirm Password do not match";
        }
 
        UserEntity user = optionalUser.get();
 
        // Encrypt new password before storing
        String encodedPassword =
                passwordEncoder.encode(newPassword);
 
        user.setPassword(encodedPassword);
 
        userRepository.save(user);
 
        return "Password Reset Successfully";
    }
 
    // ---------------------------------------------------------
    // Google reCAPTCHA Configuration
    // ---------------------------------------------------------
 
    /**
     * Google reCAPTCHA secret key defined in application properties.
     */
    @Value("${google.recaptcha.secret}")
    private String recaptchaSecret;
 
    /**
     * Google reCAPTCHA verification URL defined in application properties.
     */
    @Value("${google.recaptcha.verify.url}")
    private String recaptchaVerifyUrl;
 
    /**
     * Verifies Google reCAPTCHA token by sending
     * a request to Google's verification endpoint.
     *
     * @param token reCAPTCHA token received from client
     * @return true if captcha validation succeeds,
     *         otherwise false
     */
    public boolean verifyCaptcha(String token) {
 
        try {
 
            RestTemplate restTemplate = new RestTemplate();
 
            URI verifyUri = new URI(
                    recaptchaVerifyUrl
                    + "?secret=" + recaptchaSecret
                    + "&response=" + token);
 
            ResponseEntity<RecaptchaResponse> response =
                    restTemplate.postForEntity(
                            verifyUri,
                            null,
                            RecaptchaResponse.class);
 
            return response.getBody() != null
                    && response.getBody().isSuccess();
 
        } catch (Exception e) {
 
            return false;
        }
    }
    
    
    public String verifyOtpAndResetPassword(
            String email,
            String otp,
            String newPassword) {
 
        System.out.println("Entered Email : " + email);
 
        Optional<UserEntity> optionalUser =
                userRepository.findByUserEmail(email);
 
        UserEntity user = optionalUser.orElse(null);
 
        if (user == null) {
            return "User Not Found";
        }
 
        if (!user.getOtp().equals(otp)) {
            return "Invalid OTP";
        }
 
        String encodedPassword =
                passwordEncoder.encode(newPassword);
 
        user.setPassword(encodedPassword);
        user.setVerified(true);
        user.setOtp(null);
 
        userRepository.save(user);
 
        return "Password Reset Successfully";
    }

	@Override
	public String login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
 
	@Override

	public UserDashboardDTO getDashboard(String email) {


	    UserEntity user =

	            userRepository

	            .findByUserEmail(email)

	            .orElseThrow(

	                    () -> new RuntimeException("User Not Found")

	            );


	    List<BillEntity> bills =

	            billRepository.findByUserId(

	                    user.getUserId()

	            );


	    UserDashboardDTO dto =

	            new UserDashboardDTO();


	    dto.setUserId(

	            user.getUserId()

	    );


	    dto.setUserName(

	            user.getUserName()

	    );


	    dto.setUserEmail(

	            user.getUserEmail()

	    );


	    dto.setTotalBills(

	            bills.size()

	    );


	    dto.setPaidBills(


	            (int) bills.stream()


	                    .filter(

	                            bill -> "PAID"

	                                    .equalsIgnoreCase(

	                                            bill.getBillStatus()

	                                    )

	                    )


	                    .count()


	    );


	    dto.setUpcomingBills(


	            (int) bills.stream()


	                    .filter(

	                            bill -> "PENDING"

	                                    .equalsIgnoreCase(

	                                            bill.getBillStatus()

	                                    )

	                    )


	                    .count()


	    );


	    dto.setOverdueBills(


	            (int) bills.stream()


	                    .filter(

	                            bill -> "OVERDUE"

	                                    .equalsIgnoreCase(

	                                            bill.getBillStatus()

	                                    )

	                    )


	                    .count()


	    );


	    dto.setRecentBills(bills);


	    return dto;


	}
	
	@Override

	public UserEntity getUserProfile(String email) {


	    return userRepository

	            .findByUserEmail(email)

	            .orElseThrow(() ->

	                    new RuntimeException("User Not Found"));

	}
	 
	@Override

	public UserEntity updateUserProfile(

	        String email,

	        UserEntity updatedUser) {


	    UserEntity existingUser =

	            userRepository

	                    .findByUserEmail(email)

	                    .orElseThrow(() ->

	                            new RuntimeException("User Not Found"));


	    existingUser.setUserName(

	            updatedUser.getUserName());


	    existingUser.setGender(

	            updatedUser.getGender());


	    userRepository.save(existingUser);


	    return existingUser;

	}
	 
	
	 
	 
}
 
 