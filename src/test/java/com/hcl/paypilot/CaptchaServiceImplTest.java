package com.hcl.paypilot;


import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertTrue;


import java.lang.reflect.Field;


import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import com.hcl.paypilot.service.CaptchaServiceImpl;


class CaptchaServiceImplTest {


    private CaptchaServiceImpl captchaService;


    @BeforeEach

    void setUp() throws Exception {


        captchaService = new CaptchaServiceImpl();


        setField(

                captchaService,

                "recaptchaSecret",

                "dummy-secret");


        setField(

                captchaService,

                "recaptchaVerifyUrl",

                "https://dummy-url");

    }


    @Test

    void testVerifyCaptcha_NullToken() {


        boolean result =

                captchaService.verifyCaptcha(null);


        assertFalse(result);

    }


    @Test

    void testVerifyCaptcha_EmptyToken() {


        boolean result =

                captchaService.verifyCaptcha("");


        assertFalse(result);

    }


    @Test

    void testVerifyCaptcha_BlankToken() {


        boolean result =

                captchaService.verifyCaptcha("   ");


        assertFalse(result);

    }


    @Test

    void testVerifyCaptcha_MockToken() {


        boolean result =

                captchaService.verifyCaptcha(

                        "mock_token");


        assertTrue(result);

    }


    @Test

    void testVerifyCaptcha_GoogleTestSecret() throws Exception {


        setField(

                captchaService,

                "recaptchaSecret",

                "6LeIxAcTAAAAAGG-vFI1TnRWxMZNF65lW9xsIE1u");


        boolean result =

                captchaService.verifyCaptcha(

                        "any-token");


        assertTrue(result);

    }


    @Test

    void testVerifyCaptcha_RestClientException() {


        boolean result =

                captchaService.verifyCaptcha(

                        "real-token");


        assertFalse(result);

    }


    private void setField(

            Object target,

            String fieldName,

            Object value)

            throws Exception {


        Field field =

                target.getClass()

                        .getDeclaredField(fieldName);


        field.setAccessible(true);


        field.set(target, value);

    }

}
 