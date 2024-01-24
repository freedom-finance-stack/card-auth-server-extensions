package org.freedomfinancestack.extensions.crypto;

/** An interface for encryption and decryption of data. */
public interface IEncryption {

    /**
     * Encrypts the given text.
     *
     * @param textToEncrypt The text to encrypt.
     * @return The encrypted text.
     */
    String encrypt(String textToEncrypt);

    /**
     * Decrypts the given text.
     *
     * @param textToDecrypt The text to decrypt.
     * @return The decrypted text.
     */
    String decrypt(String textToDecrypt);
}
