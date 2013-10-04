package ru.kpfu.quantum.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.quantum.service.mailing.MailService;
import ru.kpfu.quantum.service.registration.CodeGenerator;
import ru.kpfu.quantum.spring.entities.Invite;
import ru.kpfu.quantum.spring.repository.InviteRepository;

/**
 * @author sala
 */
@Controller
public class AdminController {
    @Autowired
    InviteRepository inviteRepository;

    @Autowired
    CodeGenerator codeGenerator;

    @Autowired
    MailService mailService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String renderAdmin() {
        return "admin/admin";
    }

    @RequestMapping(value = "/admin/sendInvite", method = RequestMethod.POST)
    public String sendInvite(@RequestParam String email) {
        final String code = codeGenerator.getInviteCode();
        final Invite invite = new Invite(code);
        mailService.sendInvite(email, code);
        inviteRepository.save(invite);
        return "admin/invite/inviteSent";

    }

}
