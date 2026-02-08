package com.huuphuoc.api.common.annotation;

import com.huuphuoc.api.user.model.User;
import com.huuphuoc.api.user.repository.IUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;


public class ProcessEmail implements ConstraintValidator<JsonEmail, String> {

    private final IUserRepository iUserRepository;
    private String message;

    public ProcessEmail(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }


    @Override
    public void initialize(JsonEmail constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return true;
        }

        Optional<User> user = iUserRepository.findByEmail(email);

        if (user.isPresent()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
