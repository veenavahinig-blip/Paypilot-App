package com.hcl.paypilot.service;
 
/**

* Service contract handling Google reCAPTCHA validation.

* Verifies frontend user interaction tokens against the Google validation API.

*/

public interface CaptchaService {
 
    /**

     * Verifies the reCAPTCHA token by calling the Google reCAPTCHA API endpoint.

     * Supports a "mock_token" bypass value explicitly for local testing scenarios.

     *

     * @param token the client-side reCAPTCHA response token

     * @return true if validation succeeds or the token matches the test bypass value; false otherwise

     */

    boolean verifyCaptcha(String token);

}

 