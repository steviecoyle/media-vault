package com.scoyle.media_vault.response;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ErrorDetailsTest {

    private final String MESSAGE = "This is an error message";
    private final String PATH = "/api";
    private final String VALIDATION_KEY_1 = "title";
    private final String VALIDATION_VALUE_1 = "Must not be null";
    private final String VALIDATION_KEY_2 = "publisherId";
    private final String VALIDATION_VALUE_2 = "Must be a valid number";
    private final String VALIDATION_KEY_3 = "year";
    private final String VALIDATION_VALUE_3 = "Invalid date format";

    @Test
    void verify_error_details_constructor() {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, MESSAGE, PATH);

        assertEquals(HttpStatus.BAD_REQUEST.value(), errorDetails.getStatus());
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), errorDetails.getError());
        assertEquals(MESSAGE, errorDetails.getMessage());
        assertEquals(PATH, errorDetails.getPath());

        assertNotNull(errorDetails.getTimestamp());
        assertNull(errorDetails.getValidationErrors());
    }

    @Test
    void verify_error_details_validation_errors_property() {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, MESSAGE, PATH);

        Map<String, String> validationErrors = new HashMap<>();
        validationErrors.put(VALIDATION_KEY_1, VALIDATION_VALUE_1);
        validationErrors.put(VALIDATION_KEY_2, VALIDATION_VALUE_2);
        validationErrors.put(VALIDATION_KEY_3, VALIDATION_VALUE_3);

        errorDetails.setValidationErrors(validationErrors);

        assertEquals(3, errorDetails.getValidationErrors().size());

        assertTrue(errorDetails.getValidationErrors().containsKey(VALIDATION_KEY_1));
        assertTrue(errorDetails.getValidationErrors().containsKey(VALIDATION_KEY_2));
        assertTrue(errorDetails.getValidationErrors().containsKey(VALIDATION_KEY_3));

        assertEquals(VALIDATION_VALUE_1, errorDetails.getValidationErrors().get(VALIDATION_KEY_1));
        assertEquals(VALIDATION_VALUE_2, errorDetails.getValidationErrors().get(VALIDATION_KEY_2));
        assertEquals(VALIDATION_VALUE_3, errorDetails.getValidationErrors().get(VALIDATION_KEY_3));
    }
}
