package com.hcl.paypilot.entity;


import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;


/**

* Data Transfer Object (DTO) used to map the response received

* from the Google reCAPTCHA verification API.

*

* This class captures the validation result returned by Google,

* including the verification status, challenge timestamp,

* hostname, and any error codes generated during validation.

*

* It is used to determine whether the CAPTCHA token submitted

* by the user is valid and can be trusted.

*

* @author PayPilot Team

*/

public class RecaptchaResponse {


    /**

     * Indicates whether the CAPTCHA verification

     * was completed successfully.

     */

    private boolean success;


    /**

     * Timestamp representing when the CAPTCHA challenge

     * was successfully completed on the client side.

     *

     * The JSON property name from Google's response is

     * mapped using the @JsonProperty annotation.

     */

    @JsonProperty("challenge_ts")

    private String challengeTs;


    /**

     * The hostname of the website where the CAPTCHA

     * challenge was solved.

     */

    private String hostname;


    /**

     * List of error codes returned by Google if

     * CAPTCHA validation fails.

     */

    private List<String> errorCodes;


    // --- Getters and Setters ---


    /**

     * Checks whether reCAPTCHA verification was successful.

     *

     * @return true if verification succeeded,

     *         false otherwise

     */

    public boolean isSuccess() {

        return success;

    }


    /**

     * Sets the CAPTCHA verification status.

     *

     * @param success true if verification succeeded,

     *                false otherwise

     */

    public void setSuccess(boolean success) {

        this.success = success;

    }


    /**

     * Retrieves the timestamp indicating when

     * the CAPTCHA challenge was completed.

     *

     * @return Challenge completion timestamp

     */

    public String getChallengeTs() {

        return challengeTs;

    }


    /**

     * Sets the challenge completion timestamp.

     *

     * @param challengeTs Timestamp returned

     *                    by Google reCAPTCHA API

     */

    public void setChallengeTs(String challengeTs) {

        this.challengeTs = challengeTs;

    }


    /**

     * Retrieves the hostname on which

     * the CAPTCHA was solved.

     *

     * @return Hostname associated with the verification

     */

    public String getHostname() {

        return hostname;

    }


    /**

     * Sets the hostname associated with

     * the CAPTCHA verification.

     *

     * @param hostname Website hostname

     */

    public void setHostname(String hostname) {

        this.hostname = hostname;

    }


    /**

     * Retrieves the list of error codes returned

     * when CAPTCHA verification fails.

     *

     * @return List of error code strings

     */

    public List<String> getErrorCodes() {

        return errorCodes;

    }


    /**

     * Sets the error codes returned by the

     * Google reCAPTCHA verification service.

     *

     * @param errorCodes List of error code strings

     */

    public void setErrorCodes(List<String> errorCodes) {

        this.errorCodes = errorCodes;

    }


}
 