package io.github.emanuelcerqueira.springdatarest.shared.validation;

import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Component("beforeCreateEventListener")
public class BeforeCreateEventListener extends AbstractRepositoryEventListener {

    private final Validator validator;

    public BeforeCreateEventListener(Validator validator) {
        this.validator = validator;
    }

    @Override
    protected void onBeforeCreate(Object entity) {
        Set<ConstraintViolation<Object>> violations =  validator.validate(entity);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
}
