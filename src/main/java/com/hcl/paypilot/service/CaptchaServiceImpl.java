package com.hcl.paypilot.service;


import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;


import com.hcl.paypilot.entity.RecaptchaResponse;


import org.springframework.http.HttpEntity;

import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;

import org.springframework.util.LinkedMultiValueMap;

import org.springframework.util.MultiValueMap;

import org.springframework.web.client.RestClientException;


/**

* ============================================================================

* CAPTCHA Service Implementation

* ============================================================================

*

* This service implementation is responsible for validating Google

* reCAPTCHA tokens received from the client application.

*

* The implementation communicates with Google's reCAPTCHA Verification API

* to determine whether a CAPTCHA challenge was successfully completed.

*

* Features Supported:

* - Google reCAPTCHA Verification

* - Bot Prevention

* - Login Security Validation

* - Mock Token Support for Local Development

* - Google Test Secret Support

*

* Validation Flow:

* 1. Validate incoming token

* 2. Allow test bypass scenarios

* 3. Send request to Google Verification API

* 4. Process verification response

* 5. Return validation result

*

* Author: PayPilot Team

* ============================================================================

*/

@Service

public class CaptchaServiceImpl implements CaptchaService {


    /**

     * Google's official reCAPTCHA test secret key.

     *

     * Used for:

     * - Local Development

     * - Testing Environments

     * - QA Validation

     *

     * Official Google Test Secret:

     * 6LeIxAcTAAAAAGG-vFI1TnRWxMZNF65lW9xsIE1u

     */

    private static final String GOOGLE_TEST_SECRET =

            "6LeIxAcTAAAAAGG-vFI1TnRWxMZNF65lW9xsIE1u";


    /**

     * Google reCAPTCHA Secret Key.

     *

     * Loaded from application.properties.

     *

     * Example:

     * google.recaptcha.secret=xxxxxxxxxxxx

     */

    @Value("${google.recaptcha.secret}")

    private String recaptchaSecret;


    /**

     * Google reCAPTCHA Verification API URL.

     *

     * Loaded from application.properties.

     *

     * Example:

     * https://www.google.com/recaptcha/api/siteverify

     */

    @Value("${google.recaptcha.verify.url}")

    private String recaptchaVerifyUrl;


    /**

     * =========================================================================

     * Verify CAPTCHA Token

     * =========================================================================

     *

     * Validates the reCAPTCHA token received from the frontend.

     *

     * Validation Process:

     * - Checks for null or empty token

     * - Supports mock token for testing

     * - Supports Google test secret bypass

     * - Calls Google Verification API

     * - Processes verification response

     * - Returns validation result

     *

     * Local Testing Support:

     * - Token = mock_token

     * - Google Test Secret Key

     *

     * Example:

     * Frontend Login

     * → reCAPTCHA Solved

     * → Token Generated

     * → Token Sent To Backend

     * → Verification Performed

     * → Login Allowed/Rejected

     *

     * @param token Client-side reCAPTCHA token

     * @return true if verification succeeds,

     *         false otherwise

     */

    @Override

    public boolean verifyCaptcha(String token) {


        /**

         * Validate incoming token.

         *

         * Reject request if token is null or empty.

         */

        if (token == null || token.trim().isEmpty()) {


            return false;

        }


        /**

         * Allow bypass validation for:

         * - Local testing

         * - QA environments

         */

        if ("mock_token".equals(token)

                || GOOGLE_TEST_SECRET.equals(recaptchaSecret)) {


            return true;

        }


        try {


            /**

             * Create HTTP headers.

             *

             * Google API expects

             * application/x-www-form-urlencoded.

             */

            HttpHeaders headers = new HttpHeaders();


            headers.setContentType(

                    MediaType.APPLICATION_FORM_URLENCODED);


            /**

             * Prepare request body.

             *

             * Required Parameters:

             * - secret

             * - response

             */

            MultiValueMap<String, String> body =

                    new LinkedMultiValueMap<>();


            body.add("secret", recaptchaSecret);


            body.add("response", token);


            /**

             * Create REST client used for

             * external API communication.

             */

            RestTemplate restTemplate =

                    new RestTemplate();


            /**

             * Send verification request

             * to Google's API endpoint.

             */

            ResponseEntity<RecaptchaResponse> response =

                    restTemplate.postForEntity(

                            recaptchaVerifyUrl,

                            new HttpEntity<>(body, headers),

                            RecaptchaResponse.class);


            /**

             * Extract response body.

             */

            RecaptchaResponse recaptchaResponse =

                    response.getBody();


            /**

             * Handle unexpected empty response.

             */

            if (recaptchaResponse == null) {


                System.err.println(

                        "reCAPTCHA verification failed: "

                                + "empty response from Google.");


                return false;

            }


            /**

             * Log validation errors returned

             * by Google's API.

             */

            if (!recaptchaResponse.isSuccess()) {


                System.err.println(

                        "reCAPTCHA verification failed: "

                                + recaptchaResponse.getErrorCodes());

            }


            /**

             * Return verification result.

             */

            return recaptchaResponse.isSuccess();


        } catch (RestClientException exception) {


            /**

             * Handle communication failures.

             *

             * Possible Reasons:

             * - Network issue

             * - Invalid URL

             * - Google API unavailable

             * - Timeout

             */

            System.err.println(

                    "reCAPTCHA verification request failed: "

                            + exception.getMessage());


            return false;

        }

    }


}
 