package org.freedomfinancestack.extensions.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AES256EncryptionUtilsTest {

    private AES256EncryptionUtils aes256EncryptionUtils;

    @BeforeEach
    public void setUp() {
        aes256EncryptionUtils =
                new AES256EncryptionUtils(new AES256EncryptionConfig("53c433t2", "53c433t2"));
    }

    @Test
    public void testEncrypt_encryptsTextSuccessfully() {
        String plainText = "This is a secret message.";
        String encryptedText = aes256EncryptionUtils.encrypt(plainText);
        Assertions.assertNotNull(encryptedText);
        Assertions.assertNotEquals(plainText, encryptedText); // Ensure encryption is happening
    }

    @Test
    public void testDecrypt_decryptsTextSuccessfully() {
        String plainText = "This is a secret message.";
        String encryptedText = aes256EncryptionUtils.encrypt(plainText);

        String decryptedText = aes256EncryptionUtils.decrypt(encryptedText);

        Assertions.assertEquals(plainText, decryptedText);
    }

    @Test
    public void testEncrypt_handlesNullInput() {
        String encryptedText = aes256EncryptionUtils.encrypt(null);
        Assertions.assertNull(encryptedText);
    }

    @Test
    public void testDecrypt_handlesNullInput() {
        String decryptedText = aes256EncryptionUtils.decrypt(null);
        Assertions.assertNull(decryptedText);
    }

    @Test
    public void testDecrypt_handlesInvalidEncryptedText() {
        String invalidEncryptedText = "invalid_ciphertext";
        Assertions.assertThrows(
                EncryptionException.class,
                () -> aes256EncryptionUtils.decrypt(invalidEncryptedText));
    }
}
