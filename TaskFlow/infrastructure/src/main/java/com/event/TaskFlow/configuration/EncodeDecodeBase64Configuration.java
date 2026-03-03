package com.event.TaskFlow.configuration;

import core.password.ports.EncodeDecodeBase64Service;

import java.util.Base64;

public class EncodeDecodeBase64Configuration implements EncodeDecodeBase64Service {

    @Override
    public String encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    @Override
    public String decode(String encodedInput) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedInput);
        return new String(decodedBytes);
    }
}
