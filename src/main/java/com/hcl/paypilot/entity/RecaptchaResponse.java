package com.hcl.paypilot.entity;


import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;


/**

* ============================================================================

* reCAPTCHA Response DTO

* ============================================================================

*

* This Data Transfer Object (DTO) represents the response returned

* by Google's reCAPTCHA Verification API.

*

* The response is used to validate whether the CAPTCHA token

* provided by the user is legitimate and successfully verified.

*

* This DTO captures:

* - Verification Status

* - Challenge Timestamp

* - Hostname

* - Error Codes

*

* Google Verification API Response Example:

*

* {

*   "success": true,

*   "challenge_ts": "2024-01-01T10:00:00Z",

*   "hostname": "localhost",

*   "error-codes": []

* }

*

* This object is primarily used by:

* - CaptchaService

* - CaptchaServiceImpl

*

* Author: PayPilot Team

* ============================================================================

*/

public class RecaptchaResponse {


    /**

     * Indicates whether CAPTCHA validation

     * was completed successfully.

     *

     * Value:

     * true  = Valid CAPTCHA

     * false = Invalid CAPTCHA

     */

    private boolean success;


    /**

     * Timestamp indicating when the CAPTCHA challenge

     * was successfully solved on the client side.

     *

     * Mapped from Google's JSON property:

     * challenge_ts

     */

    @JsonProperty("challenge_ts")

    private String challengeTs;


    /**

     * Hostname of the client application where

     * the CAPTCHA challenge was completed.

     *

     * Example:

     * localhost

     * paypilot.com

     */

    private String hostname;


    /**

     * List of error codes returned by Google's

     * reCAPTCHA verification API when validation fails.

     *

     * Example:

     * [

     *   "missing-input-secret",

     *   "invalid-input-response"

     * ]

     */

    private List<String> errorCodes;


    /**

     * Retrieves CAPTCHA validation status.

     *

     * @return true if CAPTCHA verification succeeded,

     *         false otherwise

     */

    public boolean isSuccess() {


        return success;


    }


    /**

     * Sets CAPTCHA validation status.

     *

     * @param success Verification status

     */

    public void setSuccess(boolean success) {


        this.success = success;


    }


    /**

     * Retrieves CAPTCHA challenge timestamp.

     *

     * This timestamp represents when the user

     * successfully completed the CAPTCHA challenge.

     *

     * @return Challenge Timestamp

     */

    public String getChallengeTs() {


        return challengeTs;


    }


    /**

     * Sets CAPTCHA challenge timestamp.

     *

     * @param challengeTs Challenge Timestamp

     */

    public void setChallengeTs(String challengeTs) {


        this.challengeTs = challengeTs;


    }


    /**

     * Retrieves hostname associated with the

     * CAPTCHA verification request.

     *

     * @return Hostname

     */

    public String getHostname() {


        return hostname;


    }


    /**

     * Sets hostname associated with the

     * CAPTCHA verification request.

     *

     * @param hostname Hostname

     */

    public void setHostname(String hostname) {


        this.hostname = hostname;


    }


    /**

     * Retrieves the list of error codes returned

     * by Google's reCAPTCHA verification service.

     *

     * Error codes are returned when validation fails.

     *

     * @return List of error codes

     */

    public List<String> getErrorCodes() {


        return errorCodes;


    }


    /**

     * Sets the list of error codes returned

     * by Google's reCAPTCHA verification API.

     *

     * @param errorCodes List of error codes

     */

    public void setErrorCodes(List<String> errorCodes) {


        this.errorCodes = errorCodes;


    }


}
 