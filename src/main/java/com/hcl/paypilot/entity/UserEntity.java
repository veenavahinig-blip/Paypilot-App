package com.hcl.paypilot.entity;


import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;

import jakarta.persistence.Table;


/**

* ============================================================================

* User Entity

* ============================================================================

*

* This entity represents user information within the PayPilot Application.

*

* It stores complete user account details including:

* - Personal Information

* - Login Credentials

* - Bank Information

* - PAN Details

* - OTP Verification Details

* - Role Information

* - Account Verification Status

* - Wallet / Account Balance

*

* Database Table:

* USERS_TAB

*

* Features Supported:

* - User Registration

* - User Authentication

* - OTP Verification

* - Forgot Password

* - Password Reset

* - Profile Management

* - Dashboard Management

* - Banking Information Management

*

* Author: PayPilot Team

* ============================================================================

*/

@Entity

@Table(name = "users_tab")

public class UserEntity {


    /**

     * Unique identifier of the user.

     *

     * Example:

     * USER1001

     */

    @Id

    @Column(name = "user_id")

    private String userId;


    /**

     * Full name of the user.

     */

    @Column(name = "user_name")

    private String userName;


    /**

     * Registered email address of the user.

     *

     * Used for:

     * - Login

     * - OTP Verification

     * - Password Recovery

     */

    @Column(name = "user_email")

    private String userEmail;


    /**

     * Encrypted password of the user.

     *

     * Used during authentication.

     */

    @Column(name = "password")

    private String password;


    /**

     * PAN card details of the user.

     *

     * Example:

     * ABCDE1234F

     */

    @Column(name = "pandetails")

    private String panDetails;


    /**

     * User's bank account number.

     */

    @Column(name = "bankaccountnumber")

    private String bankAccountNumber;


    /**

     * IFSC code of the user's bank account.

     *

     * Example:

     * SBIN0001234

     */

    @Column(name = "ifsccode")

    private String ifscCode;


    /**

     * User's selected banking partner.

     *

     * Examples:

     * - SBI

     * - HDFC

     * - ICICI

     * - AXIS

     * - PNB

     */

    @Column(name = "bankingpartner")

    private String bankingPartner;


    /**

     * User role within the application.

     *

     * Possible Values:

     * - USER

     * - ADMIN

     */

    @Column(name = "role")

    private String role;


    /**

     * One Time Password used for:

     * - Account Verification

     * - Password Reset

     */

    @Column(name = "otp")

    private String otp;


    /**

     * Indicates whether the user account

     * has been verified.

     *

     * true  = Verified

     * false = Not Verified

     */

    @Column(name = "verified")

    private boolean verified;


    /**

     * User gender information.

     */

    @Column(name = "gender")

    private String gender;


    /**

     * Current available account balance.

     *

     * This balance can be used for:

     * - Bill Payments

     * - Scheduled Payments

     * - Auto Payments

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

     * @param userId            User Identifier

     * @param userName          User Name

     * @param userEmail         User Email

     * @param password          Password

     * @param panDetails        PAN Details

     * @param bankAccountNumber Bank Account Number

     * @param ifscCode          IFSC Code

     * @param bankingPartner    Banking Partner

     * @param role              User Role

     * @param otp               OTP

     * @param verified          Verification Status

     * @param gender            Gender

     * @param balance           Account Balance

     */

    public UserEntity(

            String userId,

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

     * Retrieves user identifier.

     *

     * @return User ID

     */

    public String getUserId() {

        return userId;

    }


    /**

     * Sets user identifier.

     *

     * @param userId User ID

     */

    public void setUserId(String userId) {

        this.userId = userId;

    }


    /**

     * Retrieves user name.

     *

     * @return User Name

     */

    public String getUserName() {

        return userName;

    }


    /**

     * Sets user name.

     *

     * @param userName User Name

     */

    public void setUserName(String userName) {

        this.userName = userName;

    }


    /**

     * Retrieves user email address.

     *

     * @return User Email

     */

    public String getUserEmail() {

        return userEmail;

    }


    /**

     * Sets user email address.

     *

     * @param userEmail User Email

     */

    public void setUserEmail(String userEmail) {

        this.userEmail = userEmail;

    }


    /**

     * Retrieves password.

     *

     * @return Password

     */

    public String getPassword() {

        return password;

    }


    /**

     * Sets password.

     *

     * @param password Password

     */

    public void setPassword(String password) {

        this.password = password;

    }


    /**

     * Retrieves PAN details.

     *

     * @return PAN Details

     */

    public String getPanDetails() {

        return panDetails;

    }


    /**

     * Sets PAN details.

     *

     * @param panDetails PAN Details

     */

    public void setPanDetails(String panDetails) {

        this.panDetails = panDetails;

    }


    /**

     * Retrieves bank account number.

     *

     * @return Bank Account Number

     */

    public String getBankAccountNumber() {

        return bankAccountNumber;

    }


    /**

     * Sets bank account number.

     *

     * @param bankAccountNumber Bank Account Number

     */

    public void setBankAccountNumber(String bankAccountNumber) {

        this.bankAccountNumber = bankAccountNumber;

    }


    /**

     * Retrieves IFSC code.

     *

     * @return IFSC Code

     */

    public String getIfscCode() {

        return ifscCode;

    }


    /**

     * Sets IFSC code.

     *

     * @param ifscCode IFSC Code

     */

    public void setIfscCode(String ifscCode) {

        this.ifscCode = ifscCode;

    }


    /**

     * Retrieves banking partner.

     *

     * @return Banking Partner

     */

    public String getBankingPartner() {

        return bankingPartner;

    }


    /**

     * Sets banking partner.

     *

     * @param bankingPartner Banking Partner

     */

    public void setBankingPartner(String bankingPartner) {

        this.bankingPartner = bankingPartner;

    }


    /**

     * Retrieves user role.

     *

     * @return Role

     */

    public String getRole() {

        return role;

    }


    /**

     * Sets user role.

     *

     * @param role User Role

     */

    public void setRole(String role) {

        this.role = role;

    }


    /**

     * Retrieves OTP.

     *

     * @return OTP

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

     * Retrieves verification status.

     *

     * @return Verification Status

     */

    public boolean isVerified() {

        return verified;

    }


    /**

     * Updates verification status.

     *

     * @param verified Verification Status

     */

    public void setVerified(boolean verified) {

        this.verified = verified;

    }


    /**

     * Retrieves gender.

     *

     * @return Gender

     */

    public String getGender() {

        return gender;

    }


    /**

     * Sets gender.

     *

     * @param gender Gender

     */

    public void setGender(String gender) {

        this.gender = gender;

    }


    /**

     * Retrieves current account balance.

     *

     * @return Account Balance

     */

    public Double getBalance() {

        return balance;

    }


    /**

     * Updates account balance.

     *

     * @param balance Account Balance

     */

    public void setBalance(Double balance) {

        this.balance = balance;

    }


}
 