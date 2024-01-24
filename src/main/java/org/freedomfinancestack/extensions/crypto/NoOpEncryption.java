package org.freedomfinancestack.extensions.crypto;

/**
 * An implementation of {@link IEncryption} that performs no encryption or decryption. This class
 * simply returns the input text as is.
 */
public class NoOpEncryption implements IEncryption {

    /** Singleton instance of {@code NoOpEncryption}. */
    public static final IEncryption INSTANCE = new NoOpEncryption();

    /**
     * Returns the input text as is, without performing any encryption.
     *
     * @param textToEncrypt The text to encrypt.
     * @return The input text as is.
     */
    @Override
    public String encrypt(String textToEncrypt) {
        return textToEncrypt;
    }

    /**
     * Returns the input text as is, without performing any decryption.
     *
     * @param textToDecrypt The text to decrypt.
     * @return The input text as is.
     */
    @Override
    public String decrypt(String textToDecrypt) {
        return textToDecrypt;
    }
}
