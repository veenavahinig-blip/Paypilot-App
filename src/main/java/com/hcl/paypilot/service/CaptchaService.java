package com.hcl.paypilot.service;


/**

* ============================================================================

* CAPTCHA Service

* ============================================================================

*

* Service interface responsible for validating Google reCAPTCHA

* tokens received from the client application.

*

* This interface acts as a contract between:

* - Authentication Module

* - User Login Module

* - CAPTCHA Verification Module

*

* The implementation communicates with Google's reCAPTCHA

* Verification API to determine whether a user has successfully

* completed the CAPTCHA challenge.

*

* Features Supported:

* - Google reCAPTCHA Validation

* - Login Security Enhancement

* - Bot Prevention

* - Mock Token Support for Local Testing

*

* Author: PayPilot Team

* ============================================================================

*/

public interface CaptchaService {


    /**

     * =========================================================================

     * Verify CAPTCHA Token

     * =========================================================================

     *

     * Validates the reCAPTCHA token received from the client-side

     * application against Google's reCAPTCHA Verification Service.

     *

     * Validation Flow:

     * - Verify token is not null or empty

     * - Allow mock token for testing environments

     * - Call Google's reCAPTCHA API

     * - Validate verification response

     * - Return verification result

     *

     * Testing Support:

     * - Supports "mock_token"

     * - Supports Google's official test secret key

     *

     * Example:

     * User Login

     * → CAPTCHA Solved

     * → Token Generated

     * → Token Sent to Backend

     * → Verification Performed

     *

     * @param token Client-side reCAPTCHA response token

     * @return true if CAPTCHA verification succeeds,

     *         false otherwise

     */

    boolean verifyCaptcha(String token);


}
 