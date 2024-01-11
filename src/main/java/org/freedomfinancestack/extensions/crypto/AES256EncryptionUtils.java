package org.freedomfinancestack.extensions.crypto;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class AES256EncryptionUtils implements EncryptionUtils {
    private final TextEncryptor encryptor;

    public AES256EncryptionUtils(AES256EncryptionConfig aES256EncryptionConfig) {
        this.encryptor =
                Encryptors.text(
                        hex(aES256EncryptionConfig.getPassword()),
                        hex(aES256EncryptionConfig.getSalt()));
    }

    private String hex(String str) {
        return String.valueOf(Hex.encode(str.getBytes()));
    }

    @Override
    public String encrypt(String textToEncrypt) {

        if (textToEncrypt != null && !textToEncrypt.isEmpty()) {
            try {
                return encryptor.encrypt(textToEncrypt);
            } catch (Exception e) {
                throw new EncryptionException("Error while performing encryption", e);
            }
        }
        return null;
    }

    @Override
    public String decrypt(String textToDecrypt) {
        if (textToDecrypt != null && !textToDecrypt.isEmpty()) {
            try {
                return encryptor.decrypt(textToDecrypt);
            } catch (Exception e) {
                throw new EncryptionException("Error while performing decryption", e);
            }
        }
        return null;
    }
}
