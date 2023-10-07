package org.freedomfinancestack.extensions.validation.rule;

import java.util.List;

import org.freedomfinancestack.extensions.utils.Util;
import org.freedomfinancestack.extensions.validation.Validator;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;

public class IsListValid<T> implements Validator<List<T>> {

    private final Validator<T> validationRuleToApply;

    public IsListValid(Validator<T> validationRuleToApply) {
        this.validationRuleToApply = validationRuleToApply;
    }

    public static <T> IsListValid<T> isListValid(Validator<T> validationRuleToApply) {
        return new IsListValid<T>(validationRuleToApply);
    }

    @Override
    public void validate(List<T> values) throws ValidationException {
        if (Util.isNullorBlank(values)) {
            return;
        }

        for (T nextElement : values) {
            validationRuleToApply.validate(nextElement);
        }
    }
}
