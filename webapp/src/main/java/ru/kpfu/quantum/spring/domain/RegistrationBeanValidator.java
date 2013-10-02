package ru.kpfu.quantum.spring.domain;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * @author sala
 */
@Component
public class RegistrationBeanValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationBean.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        final RegistrationBean target = (RegistrationBean)o;
        if(!Objects.equals(target.getCheckPassw(), target.getPassw())) {
            errors.rejectValue("checkPassw", "error.registration.checkPasswNotEqual", "Введенные пароли должны совпадать");
        }

    }
}
