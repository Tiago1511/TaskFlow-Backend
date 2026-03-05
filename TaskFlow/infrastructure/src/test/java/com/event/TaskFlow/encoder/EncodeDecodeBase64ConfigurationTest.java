package com.event.TaskFlow.encoder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotEmpty;
import static org.junit.jupiter.api.Assertions.*;

class EncodeDecodeBase64ConfigurationTest {

    private EncodeDecodeBase64Configuration service;

    @BeforeEach
    void setUp() {
        service = new EncodeDecodeBase64Configuration();
    }

    @Test
    @DisplayName("Encode and Decode Base64 should return original string")
    void encodeDecode() {
        String originalString = "Hello, World!";
        String encodedString = service.encode(originalString);
        String decodedString = service.decode(encodedString);

        assertNotEmpty(encodedString, "Encoded string should not be empty");
        assertNotEmpty(decodedString, "Decoded string should not be empty");

        assertEquals(originalString, decodedString, "Decoded string should match the original string");
    }

    @Test
    @DisplayName("Decode should throw IllegalArgumentException for invalid Base64 string")
    void decodeInvalidBase64() {
        String invalidBase64 = "InvalidBase64String!";

        assertThrows(IllegalArgumentException.class, () -> {
            service.decode(invalidBase64);
        }, "Decoding an invalid Base64 string should throw IllegalArgumentException");
    }

}