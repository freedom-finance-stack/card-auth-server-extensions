package org.freedomfinancestack.extensions.crypto;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

/** Implementation of {@link IEncryption} using AES-256 encryption algorithm. */
public class AES256Encryption implements IEncryption {
    private final TextEncryptor encryptor;

    /**
     * Constructs AES256Encryption with the specified configuration.
     *
     * @param aES256EncryptionConfig The configuration for AES-256 encryption.
     */
    public AES256Encryption(AES256EncryptionConfig aES256EncryptionConfig) {
        this.encryptor =
                Encryptors.delux(
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
