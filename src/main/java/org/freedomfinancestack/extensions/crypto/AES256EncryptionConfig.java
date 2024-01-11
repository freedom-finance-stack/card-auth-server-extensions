package org.freedomfinancestack.extensions.crypto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AES256EncryptionConfig {
    String password;
    String salt;
}
