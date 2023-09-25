package org.freedomfinancestack.extensions.hsm.message;

import org.apache.commons.lang3.StringUtils;
import org.freedomfinancestack.extensions.hsm.exception.HSMErrorCode;
import org.freedomfinancestack.extensions.hsm.exception.HSMValidationException;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.NonNull;

@Data
@Service
public class HSMMessageValidator {
    public void validateHSMMessage(@NonNull final HSMMessage hsmMessage)
            throws HSMValidationException {
        if (StringUtils.isBlank(hsmMessage.getKcv())) {
            throw new HSMValidationException(HSMErrorCode.KCV_EMPTY_IN_HSM_MESSAGE);
        }
        if (StringUtils.isBlank(hsmMessage.getData())) {
            throw new HSMValidationException(HSMErrorCode.DATA_EMPTY_IN_HSM_MESSAGE);
        }
    }
}
