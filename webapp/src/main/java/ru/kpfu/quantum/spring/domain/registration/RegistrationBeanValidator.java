package ru.kpfu.quantum.spring.domain.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.quantum.spring.entities.User;
import ru.kpfu.quantum.spring.repository.InviteRepository;
import ru.kpfu.quantum.spring.repository.UserRepository;

import java.util.Objects;

/**
 * @author sala
 */
@Component
public class RegistrationBeanValidator implements Validator {
    @Autowired
    UserRepository userRepository;
    @Autowired
    InviteRepository inviteRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationBean.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        final RegistrationBean target = (RegistrationBean)o;
        //
        final User userFromDB = userRepository.findByLoginOrEmail(target.getUserLog(), target.getUserEmail());
        if(userFromDB != null) {
            // Что то занято
            if(userFromDB.getEmail().equals(target.getUserEmail())) {
                errors.rejectValue("userEmail", "error.registration.checkEmailAlreadyExists", "Пользователь с такой электронной почтой уже зарегистрирован");
            }
            if(userFromDB.getLogin().equals(target.getUserLog())) {
                errors.rejectValue("userLog", "error.registration.checkLoginAlreadyExists", "Этот логин уже занят");
            }
        }
        if(inviteRepository.findByCodeAndUsed(target.getRegistrKey(), false) == null) {
            errors.rejectValue(
                    "registrKey",
                    "error.registration.checkRegisterKeyNotFound",
                    "Код приглашения не найден или уже был использован"
            );
        }
        //
        if(!Objects.equals(target.getCheckPassw(), target.getPassw())) {
            errors.rejectValue("checkPassw", "error.registration.checkPasswNotEqual", "Введенные пароли должны совпадать");
        }

    }
}
