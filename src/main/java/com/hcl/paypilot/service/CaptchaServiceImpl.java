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

* Service implementation responsible for validating Google reCAPTCHA

* tokens received from the client application.

*

* This service communicates with Google's reCAPTCHA verification API

* to determine whether a CAPTCHA challenge was completed successfully.

*

* It also supports local development and testing by allowing

* a mock token or Google's test secret key to bypass validation.

*

* @author PayPilot Team

*/

@Service

public class CaptchaServiceImpl implements CaptchaService {


    /**

     * Google's official test secret key used for

     * development and testing environments.

     */

    private static final String GOOGLE_TEST_SECRET =

            "6LeIxAcTAAAAAGG-vFI1TnRWxMZNF65lW9xsIE1u";


    /**

     * Secret key used to authenticate requests

     * sent to Google's reCAPTCHA verification service.

     *

     * Value is loaded from application properties.

     */

    @Value("${google.recaptcha.secret}")

    private String recaptchaSecret;


    /**

     * Google reCAPTCHA verification endpoint URL.

     *

     * Value is loaded from application properties.

     */

    @Value("${google.recaptcha.verify.url}")

    private String recaptchaVerifyUrl;


    /**

     * Verifies the reCAPTCHA token received from the client.

     *

     * This method:

     * <ul>

     * <li>Checks whether the token is null or empty.</li>

     * <li>Allows bypass for testing using a mock token.</li>

     * <li>Sends a verification request to Google's reCAPTCHA API.</li>

     * <li>Processes the API response and returns the validation result.</li>

     * </ul>

     *

     * If an exception occurs while communicating with Google,

     * the verification is considered unsuccessful.

     *

     * @param token reCAPTCHA token received from the client side

     * @return true if verification succeeds; false otherwise

     */

    @Override

    public boolean verifyCaptcha(String token) {


        /**

         * Returns false if token is missing or empty.

         */

        if (token == null || token.trim().isEmpty()) {

            return false;

        }


        /**

         * Bypass validation in local testing scenarios.

         */

        if ("mock_token".equals(token)

                || GOOGLE_TEST_SECRET.equals(recaptchaSecret)) {

            return true;

        }


        try {


            /**

             * Configure request headers for sending

             * form-urlencoded data to Google.

             */

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


            /**

             * Create request body containing the

             * secret key and CAPTCHA response token.

             */

            MultiValueMap<String, String> body =

                    new LinkedMultiValueMap<>();


            body.add("secret", recaptchaSecret);

            body.add("response", token);


            /**

             * Create RestTemplate instance for

             * communicating with external APIs.

             */

            RestTemplate restTemplate = new RestTemplate();


            /**

             * Send verification request to Google's

             * reCAPTCHA verification endpoint.

             */

            ResponseEntity<RecaptchaResponse> response =

                    restTemplate.postForEntity(

                            recaptchaVerifyUrl,

                            new HttpEntity<>(body, headers),

                            RecaptchaResponse.class

                    );


            /**

             * Extract verification response body.

             */

            RecaptchaResponse recaptchaResponse =

                    response.getBody();


            /**

             * Handle empty response from Google.

             */

            if (recaptchaResponse == null) {


                System.err.println(

                        "reCAPTCHA verification failed: empty response from Google.");


                return false;

            }


            /**

             * Log validation errors if verification fails.

             */

            if (!recaptchaResponse.isSuccess()) {


                System.err.println(

                        "reCAPTCHA verification failed: "

                                + recaptchaResponse.getErrorCodes());

            }


            /**

             * Return CAPTCHA verification result.

             */

            return recaptchaResponse.isSuccess();


        } catch (RestClientException e) {


            /**

             * Handle communication failures with

             * Google's reCAPTCHA verification service.

             */

            System.err.println(

                    "reCAPTCHA verification request failed: "

                            + e.getMessage());


            return false;

        }

    }


}
 