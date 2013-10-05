package ru.kpfu.quantum.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.quantum.service.mailing.MailService;
import ru.kpfu.quantum.spring.domain.registration.RegistrationBean;
import ru.kpfu.quantum.spring.domain.registration.RegistrationBeanValidator;
import ru.kpfu.quantum.spring.entities.Invite;
import ru.kpfu.quantum.spring.entities.User;
import ru.kpfu.quantum.spring.repository.InviteRepository;
import ru.kpfu.quantum.spring.repository.UserRepository;
import ru.kpfu.quantum.spring.utils.ValidationUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author sala
 */
@Controller
public class RegistrationController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MailService mailService;
    @Autowired
    RegistrationBeanValidator registrationBeanValidator;
    @Autowired
    InviteRepository inviteRepository;


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String renderRegistration(@RequestParam(required = false) String code,
                                     @RequestParam(required = false) String email,
                                     ModelMap modelMap) {
        final RegistrationBean registrationBean = new RegistrationBean();
        if(code != null) {
            registrationBean.setRegistrKey(code);
        }
        if(email != null) {
            registrationBean.setUserEmail(email);
        }
        modelMap.addAttribute("registrationBean", registrationBean);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String processRegistration(HttpServletRequest httpServletRequest,
                                      @Valid @ModelAttribute RegistrationBean registrationBean,
                                      BindingResult bindingResult) {
        User user = new User(registrationBean.getFirstname(),
                             registrationBean.getLastname(),
                             registrationBean.getUserEmail(), registrationBean.getUserLog(), registrationBean.getPassw());
        registrationBeanValidator.validate(registrationBean, bindingResult);
        if(bindingResult.hasErrors()) {
            ValidationUtils.includeFieldsWithErrorsMap(httpServletRequest, bindingResult);
            return "registration";
        }
        final User saved = userRepository.save(user);
        final Invite invite = inviteRepository.findByCodeAndUsed(registrationBean.getRegistrKey(), false);
        invite.setUsed(true);
        invite.setRegistrant(saved);
        inviteRepository.save(invite);
        mailService.sendRegistrationSuccessful(saved.getEmail(), saved.getFirstName(), saved.getLastName());
        return "registration/registrationSuccessful";
    }

}
