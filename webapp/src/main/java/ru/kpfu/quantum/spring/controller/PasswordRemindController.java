package ru.kpfu.quantum.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.quantum.service.mailing.MailManager;
import ru.kpfu.quantum.service.mailing.MailService;
import ru.kpfu.quantum.service.registration.CodeGenerator;
import ru.kpfu.quantum.spring.domain.remind.RemindBean;
import ru.kpfu.quantum.spring.domain.remind.RemindBeanValidator;
import ru.kpfu.quantum.spring.entities.PasswordRemind;
import ru.kpfu.quantum.spring.entities.User;
import ru.kpfu.quantum.spring.repository.PasswordRemindRepository;
import ru.kpfu.quantum.spring.repository.UserRepository;
import ru.kpfu.quantum.spring.utils.ValidationUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

/**
 * @author sala
 */
@Controller
public class PasswordRemindController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordRemindRepository passwordRemindRepository;
    @Autowired
    MailService mailService;
    @Autowired
    CodeGenerator codeGenerator;
    @Autowired
    RemindBeanValidator remindBeanValidator;


    @RequestMapping(value = "/passwordRemind", method = RequestMethod.GET)
    public String renderPasswordRemind(ModelMap modelMap) {
        modelMap.addAttribute("remindBean", new RemindBean());
        return "remind/remindRequest";
    }

    @RequestMapping(value = "/passwordRemind", method = RequestMethod.POST)
    public String processPasswordRemind(HttpServletRequest httpServletRequest, @Valid @ModelAttribute RemindBean remindBean, BindingResult bindingResult) {
        remindBeanValidator.validate(remindBean, bindingResult);
        if(bindingResult.hasErrors()) {
            ValidationUtils.includeFieldsWithErrorsMap(httpServletRequest, bindingResult);
            return "remind/remindRequest";
        }
        final User userByEmail = userRepository.findByEmail(remindBean.getEmail());
        final String remindCode = codeGenerator.getRemindCode();
        mailService.sendPasswordRemind(remindBean.getEmail(), remindCode);
        PasswordRemind passwordRemind = new PasswordRemind(userByEmail, remindCode);
        passwordRemindRepository.save(passwordRemind);
        return "remind/remindMailSent";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String renderPasswordChange(HttpServletRequest httpServletRequest, @RequestParam String code) {
        httpServletRequest.setAttribute("code", code);
        return "remind/changePassword";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String processPasswordChange(@RequestParam String password,
                                        @RequestParam String passwordRepeat,
                                        @RequestParam String code) {
        if(password == null || passwordRepeat == null || password.isEmpty() || passwordRepeat.isEmpty() ||
                !Objects.equals(password, passwordRepeat)) {
            return "remind/changePassword";
        }
        final User userByCode = passwordRemindRepository.findUserByCode(code);
        if(userByCode == null) {
            return "remind/changePassword";
        }
        userByCode.setPassword(password);
        userRepository.save(userByCode);
        return "remind/changeSuccessful";
    }
}
