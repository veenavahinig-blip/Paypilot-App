package com.hcl.paypilot.entity;
 
import jakarta.persistence.*;
 
/**
* Entity class representing a user in the PayPilot application.
*
* This class is mapped to the USERS_TAB table in Oracle Database.
* It stores user registration details, login credentials,
* bank information, OTP details, role information, and
* verification status.
*
* @author PayPilotTeam
* @version 1.0
*/
@Entity
@Table(name = "users_tab")
public class UserEntity {
 
    /**
     * Unique User ID.
     * Example: USER1001
     */
    @Id
    @Column(name = "user_id")
    private String userId;
 
    /**
     * Name of the user.
     */
    @Column(name = "user_name")
    private String userName;
 
    /**
     * Email address of the user.
     */
    @Column(name = "user_email")
    private String userEmail;
 
    /**
     * Encrypted password of the user.
     */
    @Column(name = "password")
    private String password;
 
    /**
     * PAN card details of the user.
     */
    @Column(name = "pandetails")
    private String panDetails;
 
    /**
     * Bank account number of the user.
     */
    @Column(name = "bankaccountnumber")
    private String bankAccountNumber;
 
    /**
     * IFSC code of the user's bank.
     */
    @Column(name = "ifsccode")
    private String ifscCode;
 
    /**
     * Banking partner selected by user.
     * Example: SBI, HDFC, ICICI, AXIS
     */
    @Column(name = "bankingpartner")
    private String bankingPartner;
 
    /**
     * User role.
     * Example: USER, ADMIN
     */
    @Column(name = "role")
    private String role;
 
    /**
     * One Time Password used for verification.
     */
    @Column(name = "otp")
    private String otp;
 
    /**
     * Indicates whether user account is verified.
     * true  -> Verified
     * false -> Not Verified
     */
    @Column(name = "verified")
    private boolean verified;
 
    /**
     * User gender.
     */
    @Column(name = "gender")
    private String gender;
    /**

     * Current account balance available for the user.

     * This balance is used for bill payments and transactions.

     */

    @Column(name = "balance")

    private Double balance;
 
 
    /**
     * Default Constructor.
     */
    public UserEntity() {
    }
 
    /**
     * Parameterized Constructor.
     *
     * @param userId              User ID
     * @param userName            User Name
     * @param userEmail           User Email
     * @param password            User Password
     * @param panDetails          PAN Details
     * @param bankAccountNumber   Bank Account Number
     * @param ifscCode            IFSC Code
     * @param bankingPartner      Banking Partner
     * @param role                User Role
     * @param otp                 One Time Password
     * @param verified            Verification Status
     * @param gender              Gender
     * @param balance             Balance
     */
    public UserEntity(String userId,
                      String userName,
                      String userEmail,
                      String password,
                      String panDetails,
                      String bankAccountNumber,
                      String ifscCode,
                      String bankingPartner,
                      String role,
                      String otp,
                      boolean verified,
                      String gender,
                      double balance) {
 
        super();
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.panDetails = panDetails;
        this.bankAccountNumber = bankAccountNumber;
        this.ifscCode = ifscCode;
        this.bankingPartner = bankingPartner;
        this.role = role;
        this.otp = otp;
        this.verified = verified;
        this.gender = gender;
        this.balance = balance;
    }
 
    /**
     * Gets User ID.
     *
     * @return userId
     */
    public String getUserId() {
        return userId;
    }
 
    /**
     * Sets User ID.
     *
     * @param userId User ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
 
    /**
     * Gets User Email.
     *
     * @return userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }
 
    /**
     * Sets User Email.
     *
     * @param userEmail User Email
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
 
    /**
     * Gets Password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }
 
    /**
     * Sets Password.
     *
     * @param password User Password
     */
    public void setPassword(String password) {
        this.password = password;
    }
 
    /**
     * Gets User Name.
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }
 
    /**
     * Sets User Name.
     *
     * @param userName User Name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    /**
     * Gets PAN Details.
     *
     * @return panDetails
     */
    public String getPanDetails() {
        return panDetails;
    }
 
    /**
     * Sets PAN Details.
     *
     * @param panDetails PAN Details
     */
    public void setPanDetails(String panDetails) {
        this.panDetails = panDetails;
    }
 
    /**
     * Gets Bank Account Number.
     *
     * @return bankAccountNumber
     */
    public String getBankAccountNumber() {
        return bankAccountNumber;
    }
 
    /**
     * Sets Bank Account Number.
     *
     * @param bankAccountNumber Bank Account Number
     */
    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
 
    /**
     * Gets IFSC Code.
     *
     * @return ifscCode
     */
    public String getIfscCode() {
        return ifscCode;
    }
 
    /**
     * Sets IFSC Code.
     *
     * @param ifscCode IFSC Code
     */
    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }
 
    /**
     * Gets Banking Partner.
     *
     * @return bankingPartner
     */
    public String getBankingPartner() {
        return bankingPartner;
    }
 
    /**
     * Sets Banking Partner.
     *
     * @param bankingPartner Banking Partner
     */
    public void setBankingPartner(String bankingPartner) {
        this.bankingPartner = bankingPartner;
    }
 
    /**
     * Gets User Role.
     *
     * @return role
     */
    public String getRole() {
        return role;
    }
 
    /**
     * Sets User Role.
     *
     * @param role User Role
     */
    public void setRole(String role) {
        this.role = role;
    }
 
    /**
     * Gets OTP.
     *
     * @return otp
     */
    public String getOtp() {
        return otp;
    }
 
    /**
     * Sets OTP.
     *
     * @param otp One Time Password
     */
    public void setOtp(String otp) {
        this.otp = otp;
    }
 
    /**
     * Checks whether user is verified.
     *
     * @return verification status
     */
    public boolean isVerified() {
        return verified;
    }
 
    /**
     * Sets verification status.
     *
     * @param verified true or false
     */
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
 
    /**
     * Gets Gender.
     *
     * @return gender
     */
    public String getGender() {
        return gender;
    }
 
    /**
     * Sets Gender.
     *
     * @param gender User Gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    /**
     * Gets current account balance.
     *
     * @return balance
     */
    public Double getBalance() {
        return balance;
    }
    /**
     * Sets current account balance.
     *
     * @param balance Account Balance
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }
 
}
 