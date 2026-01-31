package com.event.TaskFlow.encoder;

import core.user.ports.EncoderService;

public class PasswordEncoderImp implements EncoderService {
    @Override
    public String encode(String rawPassword) {
        return "";
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return false;
    }
}
