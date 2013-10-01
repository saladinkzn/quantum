package ru.kpfu.quantum.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.quantum.spring.dto.RegistrationBean;
import ru.kpfu.quantum.spring.entities.User;
import ru.kpfu.quantum.spring.repository.UserRepository;

/**
 * @author sala
 */
@Controller
public class RegistrationController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String renderRegistration() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute RegistrationBean registrationBean) {
        User user = new User(registrationBean.getFirstname(),
                             registrationBean.getLastname(),
                             registrationBean.getUserEmail(), registrationBean.getUserLog(), registrationBean.getPassw());
        userRepository.save(user);
        return "registration/registrationSuccessful";
    }
}
