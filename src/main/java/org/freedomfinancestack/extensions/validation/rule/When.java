package org.freedomfinancestack.extensions.validation.rule;

import org.freedomfinancestack.extensions.validation.Validator;
import org.freedomfinancestack.extensions.validation.exception.ValidationException;

public class When<T> implements Validator<T> {
    private final boolean condition;
    private final Validator<T> ifValidationRule;
    private Validator<T> elseValidationRule;

    public When(boolean condition, Validator<T> validationRule, Validator<T> elseValidationRule) {
        this.condition = condition;
        this.ifValidationRule = validationRule;
        this.elseValidationRule = elseValidationRule;
    }

    public When(boolean condition, Validator<T> validationRule) {
        this.condition = condition;
        this.ifValidationRule = validationRule;
    }

    public static <T> When<T> when(boolean condition, Validator<T> rules) {
        return new When<T>(condition, rules, null);
    }

    public When<T> elseRules(Validator<T> validationRule) {
        return new When<T>(condition, this.ifValidationRule, validationRule);
    }

    @Override
    public void validate(T value) throws ValidationException {
        if (condition) {
            this.ifValidationRule.validate(value);
        } else if (this.elseValidationRule != null) {
            this.elseValidationRule.validate(value);
        }
    }
}
