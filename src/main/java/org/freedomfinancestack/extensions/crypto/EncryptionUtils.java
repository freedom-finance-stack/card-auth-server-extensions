package org.freedomfinancestack.extensions.crypto;

public interface EncryptionUtils {
    String encrypt(String textToEncrypt);

    String decrypt(String textToDecrypt);
}
