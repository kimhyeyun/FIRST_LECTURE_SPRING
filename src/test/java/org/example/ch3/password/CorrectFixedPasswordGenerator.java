package org.example.ch3.password;

import org.example.ch3.password.PasswordGenerator;

public class CorrectFixedPasswordGenerator implements PasswordGenerator {
    @Override
    public String generatePassword() {
        return "abcdefgh";
    }
}
