package org.freedomfinancestack.extensions.crypto;

import lombok.AllArgsConstructor;
import lombok.Data;

/** Configuration class for AES-256 encryption. */
@Data
@AllArgsConstructor
public class AES256EncryptionConfig {
    String password;
    String salt;
}
