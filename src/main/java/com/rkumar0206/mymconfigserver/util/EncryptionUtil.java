package com.rkumar0206.mymconfigserver.util;

import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {

    private final TextEncryptor textEncryptor;

    public EncryptionUtil(TextEncryptor textEncryptor) {
        this.textEncryptor = textEncryptor;
    }

    public String encrypt(String plaintext) {
        return textEncryptor.encrypt(plaintext);
    }
}
