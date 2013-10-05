package ru.kpfu.quantum.spring.domain.remind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.quantum.spring.repository.UserRepository;

/**
 * @author sala
 */
@Component
public class RemindBeanValidator implements Validator {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return RemindBean.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final RemindBean remindBean = (RemindBean) target;
        if(!errors.hasFieldErrors("email")) {
            if(userRepository.findByEmail(remindBean.getEmail()) == null) {
                errors.rejectValue("email", "error.remind.checkEmailUserWasNotFound", "Пользователь с таким адресом электронной почты не найден");
            }
        }
    }
}
